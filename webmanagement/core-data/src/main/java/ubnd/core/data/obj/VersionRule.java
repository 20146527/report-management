package ubnd.core.data.obj;

public class VersionRule {
    private int id;
    private String name;
    private String createBy;
    private String folder;
    private String timeCreate;
    private String timeUpdate;
    private String updateBy;
    private int status;

    public VersionRule() {

    }

    public VersionRule(int id, String name, String createBy, String folder, String timeCreate, String timeUpdate, String updateBy, int status) {
        this.id = id;
        this.name = name;
        this.createBy = createBy;
        this.folder = folder;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.updateBy = updateBy;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
