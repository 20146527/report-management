package ubnd.web.logic.command;

import ubnd.core.dto.AttendeesDto;
import ubnd.core.web.command.AbstractCommand;


public class RoleCommand extends AbstractCommand<AttendeesDto> {
    public RoleCommand() {
        this.pojo = new AttendeesDto();
    }
    private Integer tab;
    private Integer objectId;
    private Integer operatorId;
    private Integer rolePermissionId;

    public Integer getTab() {
        return tab;
    }

    public void setTab(Integer tab) {
        this.tab = tab;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }
}
