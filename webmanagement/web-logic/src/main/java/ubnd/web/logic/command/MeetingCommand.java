package ubnd.web.logic.command;

import ubnd.core.dto.MeetingDto;
import ubnd.core.web.command.AbstractCommand;

public class MeetingCommand extends AbstractCommand<MeetingDto> {
    public MeetingCommand() {
        this.pojo = new MeetingDto();
    }
    private String timeStart;
    private String timeEnd;
    private String viewType;
    private String inputType;

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

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
