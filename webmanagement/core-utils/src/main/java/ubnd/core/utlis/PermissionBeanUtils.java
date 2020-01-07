//package ubnd.core.utlis;
//
//import ubnd.core.dto.PermissionDto;
//import ubnd.core.persistence.data.entity.PermissionEntity;
//
//public class PermissionBeanUtils {
//    public static PermissionEntity dtoToEntity(PermissionDto dto) {
//        PermissionEntity entity = new PermissionEntity();
//        entity.setPermissionId(dto.getPermissionId());
//        entity.setName(dto.getName());
//        entity.setOperatorPermissionEntity(OperatorBeanUtils.dtoToEntity(dto.getOperatorDto()));
//        entity.setObjectPermissionEntity(ObjectBeanUtils.dtoToEntity(dto.getObjectDto()));
//        return entity;
//    }
//
//    public static PermissionDto entityToDto(PermissionEntity entity) {
//        PermissionDto dto = new PermissionDto();
//        dto.setPermissionId(entity.getPermissionId());
//        dto.setName(entity.getName());
//        dto.setOperatorDto(OperatorBeanUtils.entityToDto(entity.getOperatorPermissionEntity()));
//        dto.setObjectDto(ObjectBeanUtils.entityToDTO(entity.getObjectPermissionEntity()));
//        return dto;
//    }
//
//}
