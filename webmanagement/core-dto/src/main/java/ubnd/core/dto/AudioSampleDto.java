package ubnd.core.dto;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class AudioSampleDto implements Serializable {

    private Integer audioSampleId;
    private String name;
    private String pathAudio;
    private String timeIndex;
    private Float size;
    private Integer sys;
    private SpeakerDto speakerDto;
    private Integer status;
    private Integer creUID;
    private Timestamp creDate;
    private Integer modUID;
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

    public SpeakerDto getSpeakerDto() {
        return speakerDto;
    }

    public void setSpeakerDto(SpeakerDto speakerDto) {
        this.speakerDto = speakerDto;
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
}
