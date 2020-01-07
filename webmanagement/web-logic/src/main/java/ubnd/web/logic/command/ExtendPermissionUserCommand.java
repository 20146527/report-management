package ubnd.web.logic.command;

import ubnd.core.dto.ExtendPermissionUserDto;
import ubnd.core.web.command.AbstractCommand;


public class ExtendPermissionUserCommand extends AbstractCommand<ExtendPermissionUserDto> {
    public ExtendPermissionUserCommand() {
        this.pojo = new ExtendPermissionUserDto();
    }
    private Integer tab;
    private Integer exPermissionId;
    private Integer count;
    private Integer operatorId;
    private String creUserName;

    public Integer getTab() {
        return tab;
    }

    public void setTab(Integer tab) {
        this.tab = tab;
    }

    public Integer getExPermissionId() {
        return exPermissionId;
    }

    public void setExPermissionId(Integer exPermissionId) {
        this.exPermissionId = exPermissionId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getCreUserName() {
        return creUserName;
    }

    public void setCreUserName(String creUserName) {
        this.creUserName = creUserName;
    }
}
