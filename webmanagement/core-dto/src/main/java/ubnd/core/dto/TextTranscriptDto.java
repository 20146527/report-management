package ubnd.core.dto;

import java.io.Serializable;

public class TextTranscriptDto implements Serializable {

    private Integer seq;
    private Integer key;
    private String idSpeaker;
    private double timeStart;
    private double timeEnd;
    private String text;
    private double reliabilityID;
    private double reliabilityText;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(String idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public double getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(double timeStart) {
        this.timeStart = timeStart;
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(double timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getReliabilityID() {
        return reliabilityID;
    }

    public void setReliabilityID(double reliabilityID) {
        this.reliabilityID = reliabilityID;
    }

    public double getReliabilityText() {
        return reliabilityText;
    }

    public void setReliabilityText(double reliabilityText) {
        this.reliabilityText = reliabilityText;
    }
}
