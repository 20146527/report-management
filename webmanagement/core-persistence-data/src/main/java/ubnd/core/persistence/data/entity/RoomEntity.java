package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_ID")
    private Integer roomId;

    @Column(name = "Room_Name")
    private String roomName;

    @Column(name = "Description")
    private String roomDescription;

    @Column(name = "Status")
    private Integer status;

    @OneToMany(mappedBy = "roomSessionEntity", fetch = FetchType.LAZY)
    private List<SessionEntity> listSession;

    @OneToMany(mappedBy = "roomEquipmentEntity", fetch = FetchType.LAZY)
    private List<EquipmentEntity> listEquiment;

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

    public List<SessionEntity> getListSession() {
        return listSession;
    }

    public void setListSession(List<SessionEntity> listSession) {
        this.listSession = listSession;
    }

    public List<EquipmentEntity> getListEquiment() {
        return listEquiment;
    }

    public void setListEquiment(List<EquipmentEntity> listEquiment) {
        this.listEquiment = listEquiment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
