package ubnd.core.data.obj;

public class ListWord {
    private int id;
    private String userCreate;
    private String userUpdate;
    private String timeCreate;
    private String timeUpdate;
    private int length;
    private int status;
    private String nameFile;

    public ListWord(){

    }

    public ListWord(int id, String userCreate, String userUpdate, String timeCreate, String timeUpdate, int length, int status, String nameFile) {
        this.id = id;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.length = length;
        this.status = status;
        this.nameFile = nameFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
