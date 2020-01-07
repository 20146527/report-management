package ubnd.core.utlis;

import ubnd.core.dto.ObjectDto;
import ubnd.core.dto.OperatorDto;
import ubnd.core.persistence.data.entity.ObjectEntity;
import ubnd.core.persistence.data.entity.OperatorEntity;

public class OperatorBeanUtils {

    public static OperatorEntity dtoToEntity(OperatorDto dto) {
        OperatorEntity entity = new OperatorEntity();
        entity.setOperatorID(dto.getOperatorID());
        entity.setNameOperator(dto.getNameOperator());
        entity.setCreate(dto.getCreate());
        entity.setEdit(dto.getEdit());
        entity.setView(dto.getView());
        entity.setDelete(dto.getDelete());
        entity.setLocked(dto.getLocked());
        return entity;
    }

    public static OperatorDto entityToDto(OperatorEntity entity) {
        OperatorDto dto = new OperatorDto();
        dto.setOperatorID(entity.getOperatorID());
        dto.setNameOperator(entity.getNameOperator());
        dto.setCreate(entity.getCreate());
        dto.setEdit(entity.getEdit());
        dto.setView(entity.getView());
        dto.setDelete(entity.getDelete());
        dto.setLocked(entity.getLocked());
        return dto;
    }

}
