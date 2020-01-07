package ubnd.core.data.obj;

import java.sql.Timestamp;

public class StenographyObject {
    private Integer stenoId;
    private String nameFile;
    private String meetingName;
    private String sessionName;
    private String modUID;
    private Timestamp modDate;

    public StenographyObject(Integer stenoId, String nameFile, String meetingName, String sessionName, String modUID, Timestamp modDate) {
        this.stenoId = stenoId;
        this.nameFile = nameFile;
        this.meetingName = meetingName;
        this.sessionName = sessionName;
        this.modUID = modUID;
        this.modDate = modDate;
    }

    public Integer getStenoId() {
        return stenoId;
    }

    public void setStenoId(Integer stenoId) {
        this.stenoId = stenoId;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getModUID() {
        return modUID;
    }

    public void setModUID(String modUID) {
        this.modUID = modUID;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }
}
