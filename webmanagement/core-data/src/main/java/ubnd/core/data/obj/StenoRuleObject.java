package ubnd.core.data.obj;

public class StenoRuleObject {
    private int id;
    private String key;
    private String value;
    private String creDate;
    private String modUser;
    private String creUser;
    private String modeDate;
    private int status;

    public StenoRuleObject(int id, String key, String value, String creDate, String modUser, String creUser, String modeDate, int status) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.creDate = creDate;
        this.modUser = modUser;
        this.creUser = creUser;
        this.modeDate = modeDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public String getModeDate() {
        return modeDate;
    }

    public void setModeDate(String modeDate) {
        this.modeDate = modeDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
