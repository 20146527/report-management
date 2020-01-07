package ubnd.core.dto;
public class ObjectDto {
    
    private Integer objectID;
    private ModuleDto moduleDto;
    private String nameObject;
    private String description;
    private Integer locked;
    private Integer typeObject;
    private Integer linkId;

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public ModuleDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModuleDto moduleDto) {
        this.moduleDto = moduleDto;
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
