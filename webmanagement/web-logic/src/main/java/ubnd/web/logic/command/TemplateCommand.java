package ubnd.web.logic.command;

import ubnd.core.dto.TemplateDto;
import ubnd.core.web.command.AbstractCommand;

public class TemplateCommand extends AbstractCommand<TemplateDto> {
    public TemplateCommand() {
        this.pojo = new TemplateDto();
    }

}
