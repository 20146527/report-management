package ubnd.web.logic.command;

import ubnd.core.dto.EquipmentDto;
import ubnd.core.web.command.AbstractCommand;

public class EquipmentCommand extends AbstractCommand<EquipmentDto> {
    public EquipmentCommand() {
        this.pojo = new EquipmentDto();
    }
    private Integer roomId;
    private String roomName;
    private String roomDescription;
    private Integer status;
    private Integer statusDB;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatusDB() {
        return statusDB;
    }

    public void setStatusDB(Integer statusDB) {
        this.statusDB = statusDB;
    }
}
