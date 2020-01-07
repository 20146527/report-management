package ubnd.api.gateway.config;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyServletContainerConfig extends ResourceConfig {
    public JerseyServletContainerConfig() {
        // if there are more than two packages then separate them with semicolon
        packages("ubnd.api.gateway.service");
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
    }
}
