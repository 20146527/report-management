package ubnd.core.data.obj;

public class LayoutObject {
    private Integer keyQwerty;
    private String valueQwerty;
    private String valueSteno;

    public LayoutObject(Integer keyQwerty, String valueQwerty, String valueSteno) {
        this.keyQwerty = keyQwerty;
        this.valueQwerty = valueQwerty;
        this.valueSteno = valueSteno;
    }

    public Integer getKeyQwerty() {
        return keyQwerty;
    }

    public void setKeyQwerty(Integer keyQwerty) {
        this.keyQwerty = keyQwerty;
    }

    public String getValueQwerty() {
        return valueQwerty;
    }

    public void setValueQwerty(String valueQwerty) {
        this.valueQwerty = valueQwerty;
    }

    public String getValueSteno() {
        return valueSteno;
    }

    public void setValueSteno(String valueSteno) {
        this.valueSteno = valueSteno;
    }
}
