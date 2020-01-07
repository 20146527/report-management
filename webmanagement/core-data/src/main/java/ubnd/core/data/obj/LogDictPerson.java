package ubnd.core.data.obj;


public class LogDictPerson {
    private String timeUpdate;
    private String nameDict;

    public LogDictPerson(String timeUpdate, String nameDict) {
        this.timeUpdate = timeUpdate;
        this.nameDict = nameDict;
    }


    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getNameDict() {
        return nameDict;
    }

    public void setNameDict(String nameDict) {
        this.nameDict = nameDict;
    }
}
