package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Equipment_ID")
    private Integer equipmentId;

    @ManyToOne
    @JoinColumn(name = "Room_ID")
    private RoomEntity roomEquipmentEntity;

    @Column(name = "Name")
    private String name;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Day_Start")
    private String dayStart;

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public RoomEntity getRoomEquipmentEntity() {
        return roomEquipmentEntity;
    }

    public void setRoomEquipmentEntity(RoomEntity roomEquipmentEntity) {
        this.roomEquipmentEntity = roomEquipmentEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }
}
