package ubnd.core.dto;



public class RoleModuleDto {
    private Integer roleModuleId;
    private ModuleDto moduleDto;
    private RoleDto roleDto;

    public Integer getRoleModuleId() {
        return roleModuleId;
    }

    public void setRoleModuleId(Integer roleModuleId) {
        this.roleModuleId = roleModuleId;
    }

    public ModuleDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModuleDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
}
