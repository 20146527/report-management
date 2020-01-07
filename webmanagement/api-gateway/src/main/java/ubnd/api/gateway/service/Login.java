package ubnd.api.gateway.service;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.api.gateway.client.LoginAPI24;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {

    @GET
    @Path("/hello")
    @Produces({ MediaType.TEXT_PLAIN })
    public String hello() {
        try {
            String logopt = LoginAPI24.logopt();
            JSONObject objectLogopt = new JSONObject(logopt);
            String refreshToken = objectLogopt.getString("refresh_token");

            String token = LoginAPI24.getAccessToken(refreshToken);
            JSONObject objectToken = new JSONObject(token);
            String accessToken = objectToken.getString("access_token");

            String meetingUpload = LoginAPI24.createMeetingUploadURL(accessToken);
            JSONArray jsonArray = new JSONArray(meetingUpload);
            String meetingUploadURL = jsonArray.getString(0);
            JSONObject meetingUploadObject = jsonArray.getJSONObject(1);

            String upload = LoginAPI24.uploadAudio(meetingUploadURL, meetingUploadObject, accessToken, "");

            String createMeeting = LoginAPI24.createMeeting(meetingUploadObject.getString("key"), accessToken, "");
            JSONObject createMeetingObject = new JSONObject(createMeeting);
            String meetingId = createMeetingObject.getString("id");

            String status = LoginAPI24.getStatus(meetingId, accessToken);

            System.out.println(upload);
            System.out.println(accessToken);
            System.out.println(status);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return "Đang kiểm thử";
    }
}
