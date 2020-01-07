package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "record")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Record_ID")
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "Session_ID")
    private SessionEntity sessionRecordEntity;

    @Column(name = "Name")
    private String name;

    @Column(name = "Length")
    private Float length;

    @Column(name = "Path")
    private String path;

    @Column(name = "Status")
    private String status;

    @Column(name = "Processing_Info")
    private String processingInfo;

    @OneToMany(mappedBy = "recordReportEntity", fetch = FetchType.LAZY)
    private List<ReportEntity> listReport;

    @OneToMany(mappedBy = "recordTranscriptEnity", fetch = FetchType.LAZY)
    private List<TranscriptEntity> listTranscript;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public SessionEntity getSessionRecordEntity() {
        return sessionRecordEntity;
    }

    public void setSessionRecordEntity(SessionEntity sessionRecordEntity) {
        this.sessionRecordEntity = sessionRecordEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessingInfo() {
        return processingInfo;
    }

    public void setProcessingInfo(String processingInfo) {
        this.processingInfo = processingInfo;
    }
}
