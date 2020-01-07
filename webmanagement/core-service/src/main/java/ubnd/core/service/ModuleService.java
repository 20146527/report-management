package ubnd.core.service;

import ubnd.core.dto.MeetingDto;
import ubnd.core.dto.ModuleDto;

import java.util.List;

public interface ModuleService {
    List<ModuleDto> findAll();
    ModuleDto findByID(Integer id);
}
