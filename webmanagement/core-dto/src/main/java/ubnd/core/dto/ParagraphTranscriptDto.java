package ubnd.core.dto;

import java.io.Serializable;

public class ParagraphTranscriptDto implements Serializable {

    private Integer seq;
    private String speaker;
    private double timeStart;
    private double timeEnd;
    private String text;
    private double reliabilityID;
    private double reliabilityText;

    public ParagraphTranscriptDto(Integer seq, String speaker, double timeStart, double timeEnd, String text, double reliabilityID, double reliabilityText) {
        this.seq = seq;
        this.speaker = speaker;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.text = text;
        this.reliabilityID = reliabilityID;
        this.reliabilityText = reliabilityText;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
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
