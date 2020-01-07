package ubnd.core.service.impl;

import ubnd.core.dto.RoomDto;
import ubnd.core.persistence.data.entity.RoomEntity;
import ubnd.core.service.RoomService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RoomBeanUtils;

import java.util.ArrayList;
import java.util.List;


public class RoomServiceImpl implements RoomService {
    public List<RoomDto> findAll(){
        List<RoomEntity> enities = SingletonDaoUtil.getRoomDaoInstance().findAll();
        List<RoomDto> dtos = new ArrayList<>();
        for (RoomEntity item: enities){
            RoomDto dto = RoomBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public RoomDto findById(Integer id) {
        RoomEntity roomEntity = SingletonDaoUtil.getRoomDaoInstance().findByID(id);
        return RoomBeanUtils.entityToDTO(roomEntity);
    }

    public void save(RoomDto dto) {
        RoomEntity entity = RoomBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getRoomDaoInstance().save(entity);
    }

    public RoomDto update(RoomDto dto) {
        return RoomBeanUtils.entityToDTO( SingletonDaoUtil.getRoomDaoInstance().update(RoomBeanUtils.dtoToEntity(dto)));
    }

    public int enableDisableRoom(Integer roomId, Integer status) {
        return SingletonDaoUtil.getRoomDaoInstance().edRoom(roomId, status);
    }

}
