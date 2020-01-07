package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "attendees_list")
public class AttendeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Attendees_ID")
    private Integer attendeesId;

    @ManyToOne
    @JoinColumn(name = "Session_ID")
    private SessionEntity sessionAttendeesEntity;

    @ManyToOne
    @JoinColumn(name = "Speaker_ID")
    private SpeakerEntity speakerAttendeesEntity;

    @ManyToOne
    @JoinColumn(name = "Duty_ID")
    private DutyEntity dutyEntity;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Cre_UID")
    private Integer creUID;

    @Column(name = "Cre_Date")
    private Timestamp creDate;

    @Column(name = "Mod_UID")
    private Integer modUID;

    @Column(name = "Mod_Date")
    private Timestamp modDate;

    public Integer getAttendeesId() {
        return attendeesId;
    }

    public void setAttendeesId(Integer attendeesId) {
        this.attendeesId = attendeesId;
    }

    public SessionEntity getSessionEntity() {
        return sessionAttendeesEntity;
    }

    public void setSessionEntity(SessionEntity sessionEntity) {
        this.sessionAttendeesEntity = sessionEntity;
    }

    public SpeakerEntity getSpeakerAttendeesEntity() {
        return speakerAttendeesEntity;
    }

    public void setSpeakerAttendeesEntity(SpeakerEntity speakerAttendeesEntity) {
        this.speakerAttendeesEntity = speakerAttendeesEntity;
    }

    public DutyEntity getDutyEntity() {
        return dutyEntity;
    }

    public void setDutyEntity(DutyEntity dutyEntity) {
        this.dutyEntity = dutyEntity;
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
