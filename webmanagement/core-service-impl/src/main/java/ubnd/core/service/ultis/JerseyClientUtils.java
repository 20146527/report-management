package ubnd.core.service.ultis;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JerseyClientUtils {
    public static Client getJerseyRestClient(){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(new LoggingFeature(
                Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                10000));
        return ClientBuilder.newClient(clientConfig);
    }
}
