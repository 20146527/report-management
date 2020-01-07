package ubnd.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class StenographyDto implements Serializable {

    private Integer stenoId;
    private SessionDto sessionDto;
    private String name;
    private String content;
    private Integer status;
    private Integer creUID;
    private Timestamp creDate;
    private Integer modUID;
    private Timestamp modDate;

    public Integer getStenoId() {
        return stenoId;
    }

    public void setStenoId(Integer stenoId) {
        this.stenoId = stenoId;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreUID() {
        return creUID;
    }

    public void setCreUID(Integer creUID) {
        this.creUID = creUID;
    }

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }

    public Integer getModUID() {
        return modUID;
    }

    public void setModUID(Integer modUID) {
        this.modUID = modUID;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }
}
