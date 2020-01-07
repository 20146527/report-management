package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "duty")
public class DutyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Duty_ID")
    private Integer dutyId;

    @Column(name = "Duty_Name")
    private String dutyName;

    @Column(name = "Description")
    private String dutyDescription;

    @OneToMany(mappedBy = "dutyEntity", fetch = FetchType.LAZY)
    private List<AttendeesEntity> listAttendees;

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyDescription() {
        return dutyDescription;
    }

    public void setDutyDescription(String dutyDescription) {
        this.dutyDescription = dutyDescription;
    }

    public List<AttendeesEntity> getListAttendees() {
        return listAttendees;
    }

    public void setListAttendees(List<AttendeesEntity> listAttendees) {
        this.listAttendees = listAttendees;
    }
}
