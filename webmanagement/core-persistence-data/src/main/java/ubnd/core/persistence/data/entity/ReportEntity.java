package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Report_ID")
    private Integer reportId;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Session_ID")
    private SessionEntity sessionReportEntity;

    @ManyToOne
    @JoinColumn(name = "Template_ID")
    private TemplateEntity templateReportEntity;

    @ManyToOne
    @JoinColumn(name = "Record_ID")
    private RecordEntity recordReportEntity;

    @ManyToOne
    @JoinColumn(name = "Steno_ID")
    private StenographyEntity stenographyReportEntity;

    @Column(name = "Tag")
    private String tag;

    @Column(name = "Content")
    private String content;

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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SessionEntity getSessionReportEntity() {
        return sessionReportEntity;
    }

    public void setSessionReportEntity(SessionEntity sessionReportEntity) {
        this.sessionReportEntity = sessionReportEntity;
    }

    public TemplateEntity getTemplateReportEntity() {
        return templateReportEntity;
    }

    public void setTemplateReportEntity(TemplateEntity templateReportEntity) {
        this.templateReportEntity = templateReportEntity;
    }

    public RecordEntity getRecordReportEntity() {
        return recordReportEntity;
    }

    public void setRecordReportEntity(RecordEntity recordReportEntity) {
        this.recordReportEntity = recordReportEntity;
    }

    public StenographyEntity getStenographyReportEntity() {
        return stenographyReportEntity;
    }

    public void setStenographyReportEntity(StenographyEntity stenographyReportEntity) {
        this.stenographyReportEntity = stenographyReportEntity;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
