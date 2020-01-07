package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "stenography")
public class StenographyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Steno_ID")
    private Integer stenoId;

    @ManyToOne
    @JoinColumn(name = "Session_ID")
    private SessionEntity sessionStenoEntity;

    @Column(name = "Steno_Name")
    private String name;

    @Column(name = "Content")
    private String content;

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

    @OneToMany(mappedBy = "stenographyReportEntity", fetch = FetchType.LAZY)
    private List<ReportEntity> listReport;

    public Integer getStenoId() {
        return stenoId;
    }

    public void setStenoId(Integer stenoId) {
        this.stenoId = stenoId;
    }

    public SessionEntity getSessionStenoEntity() {
        return sessionStenoEntity;
    }

    public void setSessionStenoEntity(SessionEntity sessionStenoEntity) {
        this.sessionStenoEntity = sessionStenoEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
