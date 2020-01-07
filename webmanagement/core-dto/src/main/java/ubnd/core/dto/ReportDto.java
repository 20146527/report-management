package ubnd.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class
ReportDto implements Serializable {

    private Integer reportId;
    private String name;
    private SessionDto sessionDto;
    private TemplateDto templateDto;
    private RecordDto recordDto;
    private StenographyDto stenographyDto;
    private String tag;
    private String content;
    private Integer status;
    private Integer creUID;
    private Timestamp creDate;
    private Integer modUID;
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

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }

    public TemplateDto getTemplateDto() {
        return templateDto;
    }

    public void setTemplateDto(TemplateDto templateDto) {
        this.templateDto = templateDto;
    }

    public RecordDto getRecordDto() {
        return recordDto;
    }

    public void setRecordDto(RecordDto recordDto) {
        this.recordDto = recordDto;
    }

    public StenographyDto getStenographyDto() {
        return stenographyDto;
    }

    public void setStenographyDto(StenographyDto stenographyDto) {
        this.stenographyDto = stenographyDto;
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
