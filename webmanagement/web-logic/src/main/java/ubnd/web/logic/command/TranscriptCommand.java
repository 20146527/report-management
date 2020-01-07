package ubnd.web.logic.command;

import ubnd.core.dto.TranscriptDto;
import ubnd.core.web.command.AbstractCommand;

public class TranscriptCommand extends AbstractCommand<TranscriptDto> {
    public TranscriptCommand() {
        this.pojo = new TranscriptDto();
    }
    private Integer step;
    private Integer meetingId;
    private Integer sessionId;
    private Integer recordId;
    private String speaker;

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

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
