package ubnd.core.service;

import ubnd.core.data.obj.DictObject;
import ubnd.core.dto.DictStenoDTO;
import ubnd.core.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface DictStenoService {
    void updateDefaultDist(int dictId);

    List<DictObject> listDict();

    DictObject getDefaultDict();

    DictStenoDTO update(DictStenoDTO dto);

    void save(DictStenoDTO dto);

}
