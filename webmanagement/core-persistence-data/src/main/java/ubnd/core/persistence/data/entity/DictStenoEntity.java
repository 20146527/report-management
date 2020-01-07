package ubnd.core.persistence.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dict_steno")
public class DictStenoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dict_ID")
    private Integer dictId;

    @Column(name = "Dict_Name")
    private String dictName;

    @Column(name = "Length")
    private Integer length;

    @Column(name = "Content")
    private String content;

    @Column(name = "Dict_Default")
    private Integer dictDefault;

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

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDictDefault() {
        return dictDefault;
    }

    public void setDictDefault(Integer dictDefault) {
        this.dictDefault = dictDefault;
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
