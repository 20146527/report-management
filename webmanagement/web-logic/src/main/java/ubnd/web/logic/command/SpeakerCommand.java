package ubnd.web.logic.command;

import ubnd.core.dto.SpeakerDto;
import ubnd.core.web.command.AbstractCommand;

public class SpeakerCommand extends AbstractCommand<SpeakerDto> {
    public SpeakerCommand() {
        this.pojo = new SpeakerDto();
    }
}
