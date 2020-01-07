package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "speaker")
public class SpeakerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Speaker_ID")
    private Integer speakerId;

    @Column(name = "Full_Name")
    private String fullName;

    @Column(name = "Other_Name")
    private String otherName;

    @Column(name = "Gender")
    private Integer gender;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Birthday")
    private String birthday;

    @Column(name = "Regency")
    private String regency;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Cre_UID")
    private Integer creUID;

    @Column(name = "Cre_Date")
    private Timestamp creDate;

    @Column(name = "Mod_UID")
    private Integer modUID;

    @Column(name = "Mod_Date")
    private Timestamp modDate;

    @OneToMany(mappedBy = "speakerEntity", fetch = FetchType.LAZY)
    private List<AudioSampleEntity> listAudioSample;

    @OneToMany(mappedBy = "speakerAttendeesEntity", fetch = FetchType.LAZY)
    private List<AttendeesEntity> listAttendees;

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegency() {
        return regency;
    }

    public void setRegency(String regency) {
        this.regency = regency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreUID() {
        return creUID;
    }

    public void setCreUID(Integer creUID) {
        this.creUID = creUID;
    }

    public Timestamp getCreDate() {
        return creDate;
    }

    public void setCreDate(Timestamp creDate) {
        this.creDate = creDate;
    }

    public Integer getModUID() {
        return modUID;
    }

    public void setModUID(Integer modUID) {
        this.modUID = modUID;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    public List<AudioSampleEntity> getListAudioSample() {
        return listAudioSample;
    }

    public void setListAudioSample(List<AudioSampleEntity> listAudioSample) {
        this.listAudioSample = listAudioSample;
    }

    public List<AttendeesEntity> getListAttendees() {
        return listAttendees;
    }

    public void setListAttendees(List<AttendeesEntity> listAttendees) {
        this.listAttendees = listAttendees;
    }
}
