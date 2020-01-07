package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_module")
public class RoleModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Module_ID")
    private Integer roleModuleId;

    @ManyToOne
    @JoinColumn(name = "Module_ID")
    private ModuleEntity moduleRoleEntity;

    @ManyToOne
    @JoinColumn(name = "Role_ID")
    private RoleEntity roleModuleEntity;

    public Integer getRoleModuleId() {
        return roleModuleId;
    }

    public void setRoleModuleId(Integer roleModuleId) {
        this.roleModuleId = roleModuleId;
    }

    public ModuleEntity getModuleRoleEntity() {
        return moduleRoleEntity;
    }

    public void setModuleRoleEntity(ModuleEntity moduleRoleEntity) {
        this.moduleRoleEntity = moduleRoleEntity;
    }

    public RoleEntity getRoleModuleEntity() {
        return roleModuleEntity;
    }

    public void setRoleModuleEntity(RoleEntity roleModuleEntity) {
        this.roleModuleEntity = roleModuleEntity;
    }
}
