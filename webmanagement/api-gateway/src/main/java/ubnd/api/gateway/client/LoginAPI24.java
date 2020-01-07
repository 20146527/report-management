package ubnd.api.gateway.client;

import org.glassfish.jersey.internal.guava.CacheBuilder;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.json.JSONObject;
import ubnd.api.gateway.utils.JerseyClientUtils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

public class LoginAPI24 {
    public static final String URL_LOGOPT = "http://103.238.71.103:8888/api/v2/logopt";
    public static final String URL_TOKEN = "http://103.238.71.103:8888/api/v2/token";
    public static final String URL_MEETING_UPLOAD = "http://103.238.71.103:8888/api/v2/meeting-upload-url";
    public static final String URL_MEETING = "http://103.238.71.103:8888/api/v2/meeting";
    public static final String URL_FULL_TRANSCRIPTION = "http://103.238.71.103:8888/api/v2/fulltranscription";

    public static String logopt() {
        JSONObject json = new JSONObject();
        json.put("email", "ubnd@vais.vn");
        json.put("password", "ubnd@123");
        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_LOGOPT);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public static String getAccessToken(String refreshToken) {
        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_TOKEN);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON).header("Token", refreshToken);
        Response response = builder.post(Entity.entity(null, MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public static String createMeetingUploadURL(String accessToken) {
        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_MEETING_UPLOAD);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON).header("Token", accessToken);
        Response response = builder.post(Entity.entity(null, MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public static String uploadAudio(String url, JSONObject data, String accessToken, String fullPath) throws IOException {
        final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File(fullPath));

        MultiPart multiPart = new FormDataMultiPart()
                .field("bucket", data.getString("bucket"), MediaType.TEXT_PLAIN_TYPE)
                .field("key", data.getString("key"), MediaType.TEXT_PLAIN_TYPE)
                .field("policy", data.getString("policy"), MediaType.TEXT_PLAIN_TYPE)
                .field("x-amz-algorithm", data.getString("x-amz-algorithm"), MediaType.TEXT_PLAIN_TYPE)
                .field("x-amz-credential", data.getString("x-amz-credential"), MediaType.TEXT_PLAIN_TYPE)
                .field("x-amz-date", data.getString("x-amz-date"), MediaType.TEXT_PLAIN_TYPE)
                .field("x-amz-signature", data.getString("x-amz-signature"), MediaType.TEXT_PLAIN_TYPE)
                .bodyPart(fileDataBodyPart);
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

        WebTarget target = client.target(url);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE).header("Token", accessToken);

        Response response = builder.post(Entity.entity(multiPart, multiPart.getMediaType()));
//        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
//                .post(Entity.entity(multiPart, multiPart.getMediaType()));
        multiPart.close();
        return response.readEntity(String.class);
    }

    public static String createMeeting(String key, String accessToken, String meetingName) {
        JSONObject json = new JSONObject();
        json.put("audio_path", key);
        json.put("name", meetingName);
        json.put("status", 2);

        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_MEETING);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON).header("Token", accessToken);
        Response response = builder.post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public static String getStatus(String meetingId, String accessToken) {
        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_MEETING + "?id=" + meetingId);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON).header("Token", accessToken);
        Response response = builder.get();
        return response.readEntity(String.class);
    }

    public static String getFullTranscript(String meetingId, String accessToken){
        Client client = JerseyClientUtils.getJerseyRestClient();
        WebTarget target = client.target(URL_FULL_TRANSCRIPTION + "?meeting_id=" + meetingId);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON).header("Token", accessToken);
        Response response = builder.get();
        return response.readEntity(String.class);
    }



}
