package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_module")
public class UserModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Module_ID")
    private int userModuleID;

    @ManyToOne
    @JoinColumn(name = "Module_ID")
    private ModuleEntity moduleEntity;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private UserEntity userModuleEntity;

    public int getUserModuleID() {
        return userModuleID;
    }

    public void setUserModuleID(int userModuleID) {
        this.userModuleID = userModuleID;
    }

    public ModuleEntity getModuleEntity() {
        return moduleEntity;
    }

    public void setModuleEntity(ModuleEntity moduleEntity) {
        this.moduleEntity = moduleEntity;
    }

    public UserEntity getUserModuleEntity() {
        return userModuleEntity;
    }

    public void setUserModuleEntity(UserEntity userModuleEntity) {
        this.userModuleEntity = userModuleEntity;
    }
}
