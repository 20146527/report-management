package ubnd.core.service.impl;

import ubnd.core.dto.*;
import ubnd.core.persistence.data.entity.AttendeesEntity;
import ubnd.core.persistence.data.entity.SessionEntity;
import ubnd.core.service.SessionService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.DutyBeanUtils;
import ubnd.core.utlis.MeetingBeanUtils;
import ubnd.core.utlis.SessionBeanUtils;
import ubnd.core.utlis.SpeakerBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionServiceImpl implements SessionService {
    public List<SessionDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getSessionDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<SessionDto> sessionDtoList = new ArrayList<>();
        for(SessionEntity item: (List<SessionEntity>)objects[1]){
            SessionDto sessionDto = SessionBeanUtils.entityToDTO(item);
            sessionDtoList.add(sessionDto);
        }
        return sessionDtoList;
    }

    public SessionDto findByID(Integer sessionId) {
        SessionEntity entity = SingletonDaoUtil.getSessionDaoInstance().findByID(sessionId);
        return SessionBeanUtils.entityToDTO(entity);
    }

    public SessionDto update(SessionDto dto) {
        SessionEntity entity = SessionBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getSessionDaoInstance().update(entity);
        dto = SessionBeanUtils.entityToDTO(entity);
        return dto;
    }

    public void save(SessionDto dto) {
        SessionEntity entity = SessionBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getSessionDaoInstance().save(entity);
    }

    public void validateImportSession(List<SessionImportDto> sessionImportDtos){
        List<String> names = new ArrayList<>();
        List<String> meetings = new ArrayList<>();

//        for(SessionImportDto item: sessionImportDtos){
//            if(item.isValid()){
//                names.add(item.getName());
//
//            }
//        }
    }

    @Override
    public List<SessionDto> searchSession(Integer meetingId, Integer roomId, String name, String description, Timestamp timeStart, Timestamp timeEnd, Integer status) {
        List<SessionEntity> entities = SingletonDaoUtil.getSessionDaoInstance().searchByHQL(meetingId, roomId, name, description, timeStart, timeEnd, status);
        List<SessionDto> dtos = new ArrayList<>();
        for(SessionEntity item: entities){
            SessionDto dto = SessionBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public SessionDto findSessionUpload(Integer meetingId, Integer sessionId) {
        SessionDto sessionDto = new SessionDto();
        if(sessionId == null){
            Map<String, Object> mapProperty = new HashMap<>();
            mapProperty.put("meetingEntity", meetingId);
            mapProperty.put("status", -1);
            List<SessionDto> sessionDtos = findByProperty(mapProperty, null, null, null, null);
            sessionDto = sessionDtos.get(0);
        }else {
            sessionDto = findByID(sessionId);
        }
        return sessionDto;
    }

    public void saveSessionDataImport(List<SessionImportDto> sessionImportDtos, MeetingDto meetingDto, Integer userId, Timestamp timestamp){
        for(SessionImportDto item: sessionImportDtos){
            if(item.isValid()){
                SessionEntity entity = new SessionEntity();
                entity.setMeetingEntity(MeetingBeanUtils.dtoToEntity(meetingDto));
                entity.setName(item.getName());
                //entity.setAddress(item.getAddress());
//                entity.setTimeStart();
//                entity.setTimeEnd();
                entity.setDescription(item.getDescription());
                entity.setStatus(0);
                entity.setCreUID(userId);
                entity.setCreDate(timestamp);
                entity.setModUID(userId);
                entity.setModDate(timestamp);
                //SingletonDaoUtil.getSessionDaoInstance().save(entity);
            }
        }
    }
    public void saveAttendeesDataImport(List<AttendeesImportDto> attendeesImportDtos, SessionDto sessionDto, List<SpeakerDto> speakerDtos, List<DutyDto> dutyDtos, Integer userId, Timestamp timestamp) {
        for(AttendeesImportDto item: attendeesImportDtos){
            if (item.isValid()){
                AttendeesEntity entity = new AttendeesEntity();
                SpeakerDto speakerDto = findSpeakerByName(speakerDtos, item.getFullName());
                DutyDto dutyDto = findDutyByName(dutyDtos, item.getDuty());
                entity.setSessionEntity(SessionBeanUtils.dtoToEntity(sessionDto));
                entity.setSpeakerAttendeesEntity(SpeakerBeanUtils.dtoToEntity(speakerDto));
                entity.setDutyEntity(DutyBeanUtils.dtoToEntity(dutyDto));
                entity.setStatus(0);
                entity.setCreUID(userId);
                entity.setCreDate(timestamp);
                entity.setModUID(userId);
                entity.setModDate(timestamp);
                SingletonDaoUtil.getAttendeesDaoInstance().save(entity);
            }
        }
    }

    private SpeakerDto findSpeakerByName(List<SpeakerDto> dtos, String name){
        SpeakerDto speakerDto = new SpeakerDto();
        for (SpeakerDto item: dtos){
            if(name.equals(item.getFullName())){
                return item;
            }
        }
        return speakerDto;
    }

    private DutyDto findDutyByName(List<DutyDto> dtos, String name){
        DutyDto dutyDto = new DutyDto();
        for(DutyDto item: dtos){
            if(name.equals(item.getDutyDescription())){
                return item;
            }
        }
        return dutyDto;
    }

    public Integer deleteMember(List<Integer> ids){
        return SingletonDaoUtil.getSessionDaoInstance().delete(ids);
    }
}
