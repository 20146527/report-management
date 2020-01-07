package ubnd.core.service.impl;

import ubnd.core.dto.AttendeesDto;
import ubnd.core.persistence.data.entity.AttendeesEntity;
import ubnd.core.service.AttendeesService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.AttendeesBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendeesServiceImpl implements AttendeesService {


    @Override
    public List<AttendeesDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getAttendeesDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<AttendeesDto> attendeesDtoList = new ArrayList<>();
        for(AttendeesEntity item: (List<AttendeesEntity>)objects[1]){
            AttendeesDto attendeesDto = AttendeesBeanUtils.entityToDTO(item);
            attendeesDtoList.add(attendeesDto);
        }
        return attendeesDtoList;
    }

    @Override
    public AttendeesDto findById(Integer id) {
        return AttendeesBeanUtils.entityToDTO(SingletonDaoUtil.getAttendeesDaoInstance().findByID(id));
    }

    @Override
    public AttendeesDto update(AttendeesDto dto) {
        AttendeesEntity entity = AttendeesBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getAttendeesDaoInstance().update(entity);
        dto = AttendeesBeanUtils.entityToDTO(entity);
        return dto;
    }

    @Override
    public void save(AttendeesDto dto) {
        AttendeesEntity entity = AttendeesBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getAttendeesDaoInstance().save(entity);
    }

    @Override
    public Integer deleteMember(List<Integer> ids) {
        return SingletonDaoUtil.getAttendeesDaoInstance().delete(ids);
    }

    @Override
    public List<AttendeesDto> findPreside(Integer presideId) {
        List<AttendeesEntity> entities = SingletonDaoUtil.getAttendeesDaoInstance().findPreside(presideId);
        List<AttendeesDto> dtos = new ArrayList<>();
        for(AttendeesEntity item: entities){
            AttendeesDto dto = AttendeesBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void attendance(Integer id) {

    }
}
