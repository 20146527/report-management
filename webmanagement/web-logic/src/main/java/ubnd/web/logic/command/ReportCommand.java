package ubnd.web.logic.command;

import ubnd.core.dto.ReportDto;
import ubnd.core.web.command.AbstractCommand;

public class ReportCommand extends AbstractCommand<ReportDto> {
    public ReportCommand() {
        this.pojo = new ReportDto();
    }
    private Integer step;
    private Integer meetingId;
    private Integer sessionId;
    private Integer recordId;
    private Integer stenoId;
    private Integer templateId;
    private Integer speakerId;
    private String timeStart;
    private String timeEnd;

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStenoId() {
        return stenoId;
    }

    public void setStenoId(Integer stenoId) {
        this.stenoId = stenoId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
