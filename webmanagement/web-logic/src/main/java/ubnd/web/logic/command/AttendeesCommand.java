package ubnd.web.logic.command;

import ubnd.core.dto.AttendeesDto;
import ubnd.core.web.command.AbstractCommand;


public class AttendeesCommand extends AbstractCommand<AttendeesDto> {
    public AttendeesCommand() {
        this.pojo = new AttendeesDto();
    }
    private Integer speakerId;
    private Integer sessionId;
    private Integer dutyId;

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }
}
