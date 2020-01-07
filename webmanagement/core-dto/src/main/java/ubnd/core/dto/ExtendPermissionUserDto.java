package ubnd.core.dto;


import java.sql.Timestamp;

public class ExtendPermissionUserDto {
    private Integer extendPermissionUserId;
    private UserDto userDto;
    private ObjectDto objectDto;
    private OperatorDto operatorDto;
    private Integer count;
    private Integer requestTag;
    private Integer creUID;
    private String creUserName;
    private Timestamp creDate;

    public Integer getExtendPermissionUserId() {
        return extendPermissionUserId;
    }

    public void setExtendPermissionUserId(Integer extendPermissionUserId) {
        this.extendPermissionUserId = extendPermissionUserId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ObjectDto getObjectDto() {
        return objectDto;
    }

    public void setObjectDto(ObjectDto objectDto) {
        this.objectDto = objectDto;
    }

    public OperatorDto getOperatorDto() {
        return operatorDto;
    }

    public void setOperatorDto(OperatorDto operatorDto) {
        this.operatorDto = operatorDto;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(Integer requestTag) {
        this.requestTag = requestTag;
    }

    public Integer getCreUID() {
        return creUID;
    }

    public void setCreUID(Integer creUID) {
        this.creUID = creUID;
    }

    public String getCreUserName() {
        return creUserName;
    }

    public void setCreUserName(String creUserName) {
        this.creUserName = creUserName;
    }

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }
}
