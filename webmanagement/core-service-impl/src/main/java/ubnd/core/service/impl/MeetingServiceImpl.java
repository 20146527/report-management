package ubnd.core.service.impl;

import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.RoomDto;
import ubnd.core.dto.SessionDto;
import ubnd.core.persistence.data.entity.MeetingEntity;
import ubnd.core.service.MeetingService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.MeetingBeanUtils;
import ubnd.core.utlis.SessionBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingServiceImpl implements MeetingService {

    @Override
    public List<MeetingDto> findAll() {
        List<MeetingEntity> entityList = SingletonDaoUtil.getMeetingDaoInstance().findAll();
        List<MeetingDto> dtoList = new ArrayList<>();
        for(MeetingEntity item: entityList){
            MeetingDto dto = MeetingBeanUtils.entityToDTO(item);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<MeetingDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getMeetingDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<MeetingDto> meetingDtoList = new ArrayList<MeetingDto>();
        for(MeetingEntity item: (List<MeetingEntity>)objects[1]){
            MeetingDto meetingDto = MeetingBeanUtils.entityToDTO(item);
            meetingDtoList.add(meetingDto);
        }
        return meetingDtoList;
    }

    @Override
    public MeetingDto findById(Integer meetingId) {
        MeetingEntity entity = SingletonDaoUtil.getMeetingDaoInstance().findByID(meetingId);
        MeetingDto dto = MeetingBeanUtils.entityToDTO(entity);
        return dto;
    }

    @Override
    public MeetingDto update(MeetingDto dto) {
        MeetingEntity entity = MeetingBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getMeetingDaoInstance().update(entity);
        dto = MeetingBeanUtils.entityToDTO(entity);
        return dto;
    }

    @Override
    public MeetingDto save(MeetingDto dto) {
        MeetingEntity entity = MeetingBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getMeetingDaoInstance().save(entity);
        Map<String, Object> mapProperty = new HashMap<String, Object>();
        String timeStart = dto.getTimeStart().toString();
        if(timeStart != null && timeStart.length() > 19){
            timeStart = timeStart.substring(0, 18);
        }
        String timeEnd = dto.getTimeEnd().toString();
        if(timeEnd != null && timeEnd.length() > 19){
            timeEnd = timeEnd.substring(0, 18);
        }
        mapProperty.put("status", 0);
        mapProperty.put("name", dto.getName());
        mapProperty.put("timeStart", timeStart);
        mapProperty.put("timeEnd", timeEnd);
        List<MeetingDto> list = findByProperty(mapProperty, null, null, null, null);
        dto = list.get(0);
        //create SessionDefault
        SessionDto sessionDto = new SessionDto();
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(1);

        sessionDto.setMeetingDto(dto);
        sessionDto.setName("Session Default: " + dto.getName());
        sessionDto.setRoomDto(roomDto);
        sessionDto.setTimeStart(dto.getTimeStart());
        sessionDto.setTimeEnd(dto.getTimeEnd());
        sessionDto.setDescription(dto.getDescription());
        sessionDto.setStatus(-1);
        sessionDto.setCreUID(dto.getCreUID());
        sessionDto.setCreDate(dto.getCreDate());
        sessionDto.setModUID(dto.getModUID());
        sessionDto.setModDate(dto.getModDate());
        SingletonDaoUtil.getSessionDaoInstance().save(SessionBeanUtils.dtoToEntity(sessionDto));
        return dto;
    }

    @Override
    public int delete(Integer meetingId){
        return SingletonDaoUtil.getMeetingDaoInstance().delete(meetingId);
    }

    @Override
    public List<MeetingDto> searchMeeting(String name, Timestamp timeStart, Timestamp timeEnd, Integer status) {
        List<MeetingEntity> entities = SingletonDaoUtil.getMeetingDaoInstance().searchByHQL(name, timeStart, timeEnd, status);
        List<MeetingDto> dtos = new ArrayList<>();
        for(MeetingEntity item: entities){
            MeetingDto dto = MeetingBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }


}
