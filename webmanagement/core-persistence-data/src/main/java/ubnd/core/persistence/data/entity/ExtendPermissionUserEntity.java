package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "extend_permission_user")
public class ExtendPermissionUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Extend_Permission_User_ID")
    private Integer extendPermissionUserId;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private UserEntity userExtendPermissionEntity;

    @ManyToOne
    @JoinColumn(name = "Object_ID")
    private ObjectEntity objectExtendPermissionEntity;

    @ManyToOne
    @JoinColumn(name = "Operator_ID")
    private OperatorEntity operatorExtendPermissionEntity;

    @Column(name = "Count")
    private Integer count;

    @Column(name = "Request_Tag")
    private Integer requestTag;

    @Column(name = "Cre_UID")
    private Integer creUID;

    @Column(name = "Cre_Date")
    private Timestamp creDate;

    public Integer getExtendPermissionUserId() {
        return extendPermissionUserId;
    }

    public void setExtendPermissionUserId(Integer extendPermissionUserId) {
        this.extendPermissionUserId = extendPermissionUserId;
    }

    public UserEntity getUserExtendPermissionEntity() {
        return userExtendPermissionEntity;
    }

    public void setUserExtendPermissionEntity(UserEntity userExtendPermissionEntity) {
        this.userExtendPermissionEntity = userExtendPermissionEntity;
    }

    public ObjectEntity getObjectExtendPermissionEntity() {
        return objectExtendPermissionEntity;
    }

    public void setObjectExtendPermissionEntity(ObjectEntity objectExtendPermissionEntity) {
        this.objectExtendPermissionEntity = objectExtendPermissionEntity;
    }

    public OperatorEntity getOperatorExtendPermissionEntity() {
        return operatorExtendPermissionEntity;
    }

    public void setOperatorExtendPermissionEntity(OperatorEntity operatorExtendPermissionEntity) {
        this.operatorExtendPermissionEntity = operatorExtendPermissionEntity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(Integer requestTag) {
        this.requestTag = requestTag;
    }

    public Integer getCreUID() {
        return creUID;
    }

    public void setCreUID(Integer creUID) {
        this.creUID = creUID;
    }

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }
}
