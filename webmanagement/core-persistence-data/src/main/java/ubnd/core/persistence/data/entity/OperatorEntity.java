package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operator")
public class OperatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Operator_ID")
    private Integer operatorID;

    @Column(name = "Name")
    private String nameOperator;

    @Column(name = "_create")
    private Integer create;

    @Column(name = "_edit")
    private Integer edit;

    @Column(name = "_view")
    private Integer view;

    @Column(name = "_delete")
    private Integer delete;

    @Column(name = "Locked")
    private Integer locked;

    @OneToMany(mappedBy = "operatorPermissionEntity", fetch = FetchType.LAZY)
    private List<RolePermissionEntity> listPermissionEntity;

    @OneToMany(mappedBy = "operatorExtendPermissionEntity", fetch = FetchType.LAZY)
    private List<ExtendPermissionUserEntity> listExtendPermissionEntity;

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getNameOperator() {
        return nameOperator;
    }

    public void setNameOperator(String nameOperator) {
        this.nameOperator = nameOperator;
    }

    public Integer getCreate() {
        return create;
    }

    public void setCreate(Integer create) {
        this.create = create;
    }

    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }
}
