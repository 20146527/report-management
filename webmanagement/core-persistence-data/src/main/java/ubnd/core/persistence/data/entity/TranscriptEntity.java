package ubnd.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "transcript")
public class TranscriptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transcript_ID")
    private Integer transcriptId;

    @ManyToOne
    @JoinColumn(name = "Record_ID")
    private RecordEntity recordTranscriptEnity;

    @Column(name = "Speaker_ID")
    private String speakerId;

    @Column(name = "Audio_Path")
    private String audioPath;

    @Column(name = "Content")
    private String content;

    @Column(name = "Json_Path")
    private String jsonPath;

    @Column(name = "XML_Path")
    private String xmlPath;

    public Integer getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(Integer transcriptId) {
        this.transcriptId = transcriptId;
    }

    public RecordEntity getRecordTranscriptEnity() {
        return recordTranscriptEnity;
    }

    public void setRecordTranscriptEnity(RecordEntity recordTranscriptEnity) {
        this.recordTranscriptEnity = recordTranscriptEnity;
    }

    public String getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }
}
