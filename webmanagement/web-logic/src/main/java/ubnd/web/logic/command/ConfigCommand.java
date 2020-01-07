package ubnd.web.logic.command;

import ubnd.core.dto.ConfigDto;
import ubnd.core.web.command.AbstractCommand;

public class ConfigCommand extends AbstractCommand<ConfigDto> {
    public ConfigCommand() {
        this.pojo = new ConfigDto();
    }
    private String config; //type config
    private String data;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
