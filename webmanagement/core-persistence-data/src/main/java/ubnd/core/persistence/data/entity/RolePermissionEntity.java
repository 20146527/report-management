package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_permission")
public class RolePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Permission_ID")
    private Integer rolePermissionId;

    @ManyToOne
    @JoinColumn(name = "Role_ID")
    private RoleEntity rolePermissionEntity;

    @ManyToOne
    @JoinColumn(name = "Object_ID")
    private ObjectEntity objectPermissionEntity;

    @ManyToOne
    @JoinColumn(name = "Operator_ID")
    private OperatorEntity operatorPermissionEntity;

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public RoleEntity getRolePermissionEntity() {
        return rolePermissionEntity;
    }

    public void setRolePermissionEntity(RoleEntity rolePermissionEntity) {
        this.rolePermissionEntity = rolePermissionEntity;
    }

    public ObjectEntity getObjectPermissionEntity() {
        return objectPermissionEntity;
    }

    public void setObjectPermissionEntity(ObjectEntity objectPermissionEntity) {
        this.objectPermissionEntity = objectPermissionEntity;
    }

    public OperatorEntity getOperatorPermissionEntity() {
        return operatorPermissionEntity;
    }

    public void setOperatorPermissionEntity(OperatorEntity operatorPermissionEntity) {
        this.operatorPermissionEntity = operatorPermissionEntity;
    }
}
