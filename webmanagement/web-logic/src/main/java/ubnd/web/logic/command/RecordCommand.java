package ubnd.web.logic.command;

import ubnd.core.dto.RecordDto;
import ubnd.core.web.command.AbstractCommand;

public class RecordCommand extends AbstractCommand<RecordDto> {
    public RecordCommand() {
        this.pojo = new RecordDto();
    }
    private String timeStart;
    private String timeEnd;

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
