package ubnd.web.logic.command;

import ubnd.core.dto.StenographyDto;
import ubnd.core.web.command.AbstractCommand;

public class StenographyCommand extends AbstractCommand<StenographyDto> {
    public StenographyCommand(){
        this.pojo = new StenographyDto();
    }
    private String selectUploadFolder;
    private String choseTime;
    private String selectType;
    private String selectTime;

    public String getSelectUploadFolder() {
        return selectUploadFolder;
    }

    public void setSelectUploadFolder(String selectUploadFolder) {
        this.selectUploadFolder = selectUploadFolder;
    }

    public String getChoseTime() {
        return choseTime;
    }

    public void setChoseTime(String choseTime) {
        this.choseTime = choseTime;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }
}
