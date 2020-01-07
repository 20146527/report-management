package ubnd.web.logic.command;

import ubnd.core.dto.RoleDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.command.AbstractCommand;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDto> {
    public UserCommand() {
        this.pojo = new UserDto();
    }
    private String confirmPassword;
    private String password;
    private List<RoleDto> roles;
    private Integer roleId;
    private Integer userId;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
