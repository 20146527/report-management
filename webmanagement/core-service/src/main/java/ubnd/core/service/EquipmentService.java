package ubnd.core.service;


import ubnd.core.dto.EquipmentDto;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    List<EquipmentDto> findAll();
    List<EquipmentDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    EquipmentDto findByID(Integer equipmentId);
    EquipmentDto update(EquipmentDto dto);
    void save(EquipmentDto dto);
    int enableDisable(Integer equipmentId, Integer status);
}
