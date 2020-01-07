package ubnd.core.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import ubnd.core.dto.RecordDto;
import ubnd.core.persistence.data.entity.RecordEntity;
import ubnd.core.service.RecordService;
import ubnd.core.service.ultis.API24;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.RecordBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordServiceImpl implements RecordService {

    public List<RecordDto> findAll() {
        List<RecordEntity> entityList = SingletonDaoUtil.getRecordDaoInstance().findAll();
        List<RecordDto> list = new ArrayList<>();
        for(RecordEntity item: entityList){
            RecordDto dto = RecordBeanUtils.entityToDTO(item);
            list.add(dto);
        }
        return list;
    }

    public List<RecordDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getRecordDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<RecordDto> list = new ArrayList<>();
        for(RecordEntity item: (List<RecordEntity>)objects[1]){
            RecordDto dto = RecordBeanUtils.entityToDTO(item);
            list.add(dto);
        }
        return list;
    }

    public RecordDto findById(Integer recordId) {
        RecordEntity entity = SingletonDaoUtil.getRecordDaoInstance().findByID(recordId);
        return RecordBeanUtils.entityToDTO(entity);
    }

    public RecordDto updateRecord(RecordDto recordDto) {
        RecordEntity entity = RecordBeanUtils.dtoToEntity(recordDto);
        entity = SingletonDaoUtil.getRecordDaoInstance().update(entity);
        recordDto = RecordBeanUtils.entityToDTO(entity);
        return recordDto;
    }

    public void saveRecord(RecordDto recordDto) {
        RecordEntity entity = RecordBeanUtils.dtoToEntity(recordDto);
        SingletonDaoUtil.getRecordDaoInstance().save(entity);
    }

    public String uploadAudio(String fullPath) {
        JSONObject json = new JSONObject();
        try {
            String logopt = API24.logopt();
            JSONObject objectLogopt = new JSONObject(logopt);
            String refreshToken = objectLogopt.getString("refresh_token");

            String token = API24.getAccessToken(refreshToken);
            JSONObject objectToken = new JSONObject(token);
            String accessToken = objectToken.getString("access_token");

            String meetingUpload = API24.createMeetingUploadURL(accessToken);
            JSONArray jsonArray = new JSONArray(meetingUpload);
            String meetingUploadURL = jsonArray.getString(0);
            JSONObject meetingUploadObject = jsonArray.getJSONObject(1);

            String upload = API24.uploadAudio(meetingUploadURL, meetingUploadObject, accessToken, fullPath);

            String meetingName = "Meeting VNIST DEMO";
            String createMeeting = API24.createMeeting(meetingUploadObject.getString("key"), accessToken, meetingName);
            JSONObject createMeetingObject = new JSONObject(createMeeting);
            String meetingId = createMeetingObject.getString("id");

            json.put("url", API24.URL_MEETING);
            json.put("accessToken", accessToken);
            json.put("meetingId", meetingId);
            //String status = API24.getStatus(meetingId, accessToken);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public String getFullTranscription(String token, String id) {
        String transcription = API24.getFullTranscript(id, token);
        return transcription;
    }


}
