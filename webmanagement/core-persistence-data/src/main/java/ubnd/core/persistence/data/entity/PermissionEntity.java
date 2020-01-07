//package ubnd.core.persistence.data.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "permission")
//public class PermissionEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Permission_ID")
//    private Integer permissionId;
//
//    @Column(name = "Name")
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "Object_ID")
//    private ObjectEntity objectPermissionEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "Operator_ID")
//    private OperatorEntity operatorPermissionEntity;
//
//    @OneToMany(mappedBy = "permissionRoleEntity", fetch = FetchType.LAZY)
//    private List<RolePermissionEntity> listRolePermission;
//
//    public Integer getPermissionId() {
//        return permissionId;
//    }
//
//    public void setPermissionId(Integer permissionId) {
//        this.permissionId = permissionId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public OperatorEntity getOperatorPermissionEntity() {
//        return operatorPermissionEntity;
//    }
//
//    public void setOperatorPermissionEntity(OperatorEntity operatorPermissionEntity) {
//        this.operatorPermissionEntity = operatorPermissionEntity;
//    }
//
//    public ObjectEntity getObjectPermissionEntity() {
//        return objectPermissionEntity;
//    }
//
//    public void setObjectPermissionEntity(ObjectEntity objectPermissionEntity) {
//        this.objectPermissionEntity = objectPermissionEntity;
//    }
//}
