package ubnd.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecordDto implements Serializable {

    private Integer recordId;
    private SessionDto sessionDto;
    private String name;
    private Float length;
    private String path;
    private String status;
    private String processingInfo;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
