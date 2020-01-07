//package ubnd.core.utlis;
//
//import ubnd.core.dto.ObjectModuleDto;
//import ubnd.core.dto.OperatorDto;
//import ubnd.core.persistence.data.entity.ObjectModuleEntity;
//import ubnd.core.persistence.data.entity.OperatorEntity;
//
//public class ObjectModuleBeanUtils {
//    public static ObjectModuleEntity dtoToEntity(ObjectModuleDto dto) {
//        ObjectModuleEntity entity = new ObjectModuleEntity();
//        entity.setObjectModuleID(dto.getObjectModuleID());
//        entity.setModuleEntity(ModuleBeanUtils.dtoToEntity(dto.getModuleDto()));
//        entity.setObjectEntity(ObjectBeanUtils.dtoToEntity(dto.getObjectDto()));
//        return entity;
//    }
//
//    public static ObjectModuleDto entityToDto(ObjectModuleEntity entity) {
//        ObjectModuleDto dto = new ObjectModuleDto();
//        dto.setObjectModuleID(dto.getObjectModuleID());
//        dto.setModuleDto(ModuleBeanUtils.entityToDTO(entity.getModuleEntity()));
//        dto.setObjectDto(ObjectBeanUtils.entityToDTO(entity.getObjectEntity()));
//        return dto;
//    }
//
//}
