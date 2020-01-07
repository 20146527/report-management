package ubnd.web.logic.command;

import ubnd.core.dto.DictStenoDTO;
import ubnd.core.web.command.AbstractCommand;

public class ManagerDictStenoCommand extends AbstractCommand<DictStenoDTO> {
    public ManagerDictStenoCommand() {
        this.pojo = new DictStenoDTO();
    }
    private boolean checkDictDefault;

    public boolean isCheckDictDefault() {
        return checkDictDefault;
    }

    public void setCheckDictDefault(boolean checkDictDefault) {
        this.checkDictDefault = checkDictDefault;
    }
}
