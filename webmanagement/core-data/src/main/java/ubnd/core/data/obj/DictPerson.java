package ubnd.core.data.obj;

import java.sql.Timestamp;

public class DictPerson {
    private int idCode;
    private String codeSteno;
    private String word;
    private int status;
    private Timestamp creDate;
    private Timestamp modUpdate;

    public DictPerson(int idCode, String codeSteno, String word, int status, Timestamp creDate, Timestamp modUpdate) {
        this.idCode = idCode;
        this.codeSteno = codeSteno;
        this.word = word;
        this.status = status;
        this.creDate = creDate;
        this.modUpdate = modUpdate;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getCodeSteno() {
        return codeSteno;
    }

    public void setCodeSteno(String codeSteno) {
        this.codeSteno = codeSteno;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }

    public Timestamp getModUpdate() {
        return modUpdate;
    }

    public void setModUpdate(Timestamp modUpdate) {
        this.modUpdate = modUpdate;
    }
}
