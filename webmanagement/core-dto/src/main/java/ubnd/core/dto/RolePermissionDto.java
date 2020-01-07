package ubnd.core.dto;



public class RolePermissionDto {
    private Integer rolePermissionId;
    private RoleDto roleDto;
    private ObjectDto objectDto;
    private OperatorDto operatorDto;

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public ObjectDto getObjectDto() {
        return objectDto;
    }

    public void setObjectDto(ObjectDto objectDto) {
        this.objectDto = objectDto;
    }

    public OperatorDto getOperatorDto() {
        return operatorDto;
    }

    public void setOperatorDto(OperatorDto operatorDto) {
        this.operatorDto = operatorDto;
    }
}
