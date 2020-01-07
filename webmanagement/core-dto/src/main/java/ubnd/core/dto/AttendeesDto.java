package ubnd.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class AttendeesDto implements Serializable {

    private Integer attendeesId;
    private SessionDto sessionDto;
    private SpeakerDto speakerDto;
    private DutyDto dutyDto;
    private Integer status;
    private Integer creUID;
    private Timestamp creDate;
    private Integer modUID;
    private Timestamp modDate;

    public Integer getAttendeesId() {
        return attendeesId;
    }

    public void setAttendeesId(Integer attendeesId) {
        this.attendeesId = attendeesId;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }

    public SpeakerDto getSpeakerDto() {
        return speakerDto;
    }

    public void setSpeakerDto(SpeakerDto speakerDto) {
        this.speakerDto = speakerDto;
    }

    public DutyDto getDutyDto() {
        return dutyDto;
    }

    public void setDutyDto(DutyDto dutyDto) {
        this.dutyDto = dutyDto;
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
