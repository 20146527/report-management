package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audio_sample")
public class AudioSampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Audio_Sample_ID")
    private Integer audioSampleId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Path_Audio")
    private String pathAudio;

    @Column(name = "Time_Index")
    private String timeIndex;

    @Column(name = "Size")
    private Float size;

    @Column(name = "Sys")
    private Integer sys;

    @ManyToOne
    @JoinColumn(name = "Speaker_ID")
    private SpeakerEntity speakerEntity;

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


    public Integer getAudioSampleId() {
        return audioSampleId;
    }

    public void setAudioSampleId(Integer audioSampleId) {
        this.audioSampleId = audioSampleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathAudio() {
        return pathAudio;
    }

    public void setPathAudio(String pathAudio) {
        this.pathAudio = pathAudio;
    }

    public String getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(String timeIndex) {
        this.timeIndex = timeIndex;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
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

    public SpeakerEntity getSpeakerEntity() {
        return speakerEntity;
    }

    public void setSpeakerEntity(SpeakerEntity speakerEntity) {
        this.speakerEntity = speakerEntity;
    }
}
