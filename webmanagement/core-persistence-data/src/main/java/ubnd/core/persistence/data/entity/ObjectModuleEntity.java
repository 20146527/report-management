//package ubnd.core.persistence.data.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "object_module")
//public class ObjectModuleEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Object_Module_ID")
//    private Integer objectModuleID;
//
//    @ManyToOne
//    @JoinColumn(name = "Module_ID")
//    private ModuleEntity moduleEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "Object_ID")
//    private ObjectEntity objectEntity;
//
//    public Integer getObjectModuleID() {
//        return objectModuleID;
//    }
//
//    public void setObjectModuleID(Integer objectModuleID) {
//        this.objectModuleID = objectModuleID;
//    }
//
//    public ModuleEntity getModuleEntity() {
//        return moduleEntity;
//    }
//
//    public void setModuleEntity(ModuleEntity moduleEntity) {
//        this.moduleEntity = moduleEntity;
//    }
//
//    public ObjectEntity getObjectEntity() {
//        return objectEntity;
//    }
//
//    public void setObjectEntity(ObjectEntity objectEntity) {
//        this.objectEntity = objectEntity;
//    }
//}
