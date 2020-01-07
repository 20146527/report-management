package ubnd.web.logic.command;

import ubnd.core.dto.AudioSampleDto;
import ubnd.core.web.command.AbstractCommand;

public class AudioSampleCommand extends AbstractCommand<AudioSampleDto> {
    public AudioSampleCommand() {
        this.pojo = new AudioSampleDto();
    }
    private Integer speakerId;
    private String nameFile;

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }
}
