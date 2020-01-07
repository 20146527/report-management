package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "Role_ID")
    private Integer roleId;

    @Column(name = "Role_Name")
    private String roleName;

    @Column(name = "Description")
    private String roleDescription;

    @OneToMany(mappedBy = "rolePermissionEntity", fetch = FetchType.LAZY)
    private List<RolePermissionEntity> listRolePermission;

    @OneToMany(mappedBy = "roleUserEntity", fetch = FetchType.LAZY)
    private List<UserRoleEntity> listUserRole;

    @OneToMany(mappedBy = "roleModuleEntity", fetch = FetchType.LAZY)
    private List<RoleModuleEntity> listRoleModule;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
