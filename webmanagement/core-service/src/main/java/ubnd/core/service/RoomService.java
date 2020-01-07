package ubnd.core.service;

import ubnd.core.dto.DutyDto;
import ubnd.core.dto.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> findAll();
    RoomDto findById(Integer id);
    void save(RoomDto dto);
    RoomDto update(RoomDto dto);
    int enableDisableRoom(Integer roomId, Integer status);
}
