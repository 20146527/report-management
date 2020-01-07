package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Session_ID")
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(name = "Meeting_ID")
    private MeetingEntity meetingEntity;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Room_ID")
    private RoomEntity roomSessionEntity;

    @Column(name = "Time_Start")
    private Timestamp timeStart;

    @Column(name = "Time_End")
    private Timestamp timeEnd;

    @Column(name = "Description")
    private String description;

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

    @OneToMany(mappedBy = "sessionAttendeesEntity", fetch = FetchType.LAZY)
    private List<AttendeesEntity> listAttendees;

    @OneToMany(mappedBy = "sessionRecordEntity", fetch = FetchType.LAZY)
    private List<RecordEntity> listRecord;

    @OneToMany(mappedBy = "sessionStenoEntity", fetch = FetchType.LAZY)
    private List<StenographyEntity> listSteno;

    @OneToMany(mappedBy = "sessionReportEntity", fetch = FetchType.LAZY)
    private List<ReportEntity> listReport;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public MeetingEntity getMeetingEntity() {
        return meetingEntity;
    }

    public void setMeetingEntity(MeetingEntity meetingEntity) {
        this.meetingEntity = meetingEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomEntity getRoomSessionEntity() {
        return roomSessionEntity;
    }

    public void setRoomSessionEntity(RoomEntity roomSessionEntity) {
        this.roomSessionEntity = roomSessionEntity;
    }

    public List<StenographyEntity> getListSteno() {
        return listSteno;
    }

    public void setListSteno(List<StenographyEntity> listSteno) {
        this.listSteno = listSteno;
    }

    public List<ReportEntity> getListReport() {
        return listReport;
    }

    public void setListReport(List<ReportEntity> listReport) {
        this.listReport = listReport;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<AttendeesEntity> getListAttendees() {
        return listAttendees;
    }

    public void setListAttendees(List<AttendeesEntity> listAttendees) {
        this.listAttendees = listAttendees;
    }

    public List<RecordEntity> getListRecord() {
        return listRecord;
    }

    public void setListRecord(List<RecordEntity> listRecord) {
        this.listRecord = listRecord;
    }
}
