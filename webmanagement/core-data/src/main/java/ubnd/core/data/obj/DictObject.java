package ubnd.core.data.obj;

import ubnd.core.dto.DictStenoDTO;

public class DictObject {
    private DictStenoDTO dto;
    private String userName;

    public DictObject(DictStenoDTO dto, String userName) {
        this.dto = dto;
        this.userName = userName;
    }

    public DictStenoDTO getDto() {
        return dto;
    }

    public void setDto(DictStenoDTO dto) {
        this.dto = dto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
