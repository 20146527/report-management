package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modules")
public class ModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Module_ID")
    private int moduleID;

    @Column(name = "Name")
    private String nameModule;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "moduleEntity", fetch = FetchType.LAZY)
    private List<UserModuleEntity> listModule;

    @OneToMany(mappedBy = "moduleObjectEntity", fetch = FetchType.LAZY)
    private List<ObjectEntity> listObjectEntity;

    @OneToMany(mappedBy = "moduleRoleEntity", fetch = FetchType.LAZY)
    private List<RoleModuleEntity> listRoleModuleEntity;


    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserModuleEntity> getListModule() {
        return listModule;
    }

    public void setListModule(List<UserModuleEntity> listModule) {
        this.listModule = listModule;
    }
}
