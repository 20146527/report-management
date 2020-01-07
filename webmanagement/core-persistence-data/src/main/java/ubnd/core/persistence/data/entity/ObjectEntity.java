package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "object")
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Object_ID")
    private Integer objectID;

    @ManyToOne
    @JoinColumn(name = "Module_ID")
    private ModuleEntity moduleObjectEntity;

    @Column(name = "Name_Object")
    private String nameObject;

    @Column(name = "Description")
    private String description;

    @Column(name = "Locked")
    private Integer locked;

    @Column(name = "Type_Object")
    private Integer typeObject;

    @Column(name = "Link_ID")
    private Integer linkId;

    @OneToMany(mappedBy = "objectPermissionEntity", fetch = FetchType.LAZY)
    private List<RolePermissionEntity> listPermissionEntity;

    @OneToMany(mappedBy = "objectExtendPermissionEntity", fetch = FetchType.LAZY)
    private List<ExtendPermissionUserEntity> listExtendPermissionEntity;

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public ModuleEntity getModuleObjectEntity() {
        return moduleObjectEntity;
    }

    public void setModuleObjectEntity(ModuleEntity moduleObjectEntity) {
        this.moduleObjectEntity = moduleObjectEntity;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(Integer typeObject) {
        this.typeObject = typeObject;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }
}
