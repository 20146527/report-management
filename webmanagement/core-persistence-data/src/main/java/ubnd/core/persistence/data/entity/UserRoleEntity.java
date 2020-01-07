package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Role_ID")
    private Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private UserEntity userRoleEntity;

    @ManyToOne
    @JoinColumn(name = "Role_ID")
    private RoleEntity roleUserEntity;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserEntity getUserRoleEntity() {
        return userRoleEntity;
    }

    public void setUserRoleEntity(UserEntity userRoleEntity) {
        this.userRoleEntity = userRoleEntity;
    }

    public RoleEntity getRoleUserEntity() {
        return roleUserEntity;
    }

    public void setRoleUserEntity(RoleEntity roleUserEntity) {
        this.roleUserEntity = roleUserEntity;
    }
}
