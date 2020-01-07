package ubnd.core.service.ultis;


import ubnd.core.dao.AudioSampleDao;
import ubnd.core.dao.ConfigDao;
import ubnd.core.dao.impl.*;
import ubnd.core.dto.AudioSampleDto;

public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static DictStenoDaoImpl dictStenoDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static SpeakerDaoImpl speakerDaoImpl = null;
    private static AudioSampleDaoImpl audioSampleDaoImpl = null;
    private static MeetingDaoImpl meetingDaoImpl = null;
    private static SessionDaoImpl sessionDaoImpl = null;
    private static DutyDaoImpl dutyDaoImpl = null;
    private static AttendeesDaoImpl attendeesDaoImpl = null;
    private static TemplateDaoImpl templateDaoImpl = null;
    private static ReportDaoImpl reportDaoImpl = null;
    private static RoomDaoImpl roomDaoImpl = null;
    private static EquipmentDaoImpl equipmentDaoImpl = null;
    private static RecordDaoImpl recordDaoImpl = null;
    private static TranscriptDaoImpl transcriptDaoImpl = null;
    private static ConfigDaoImpl configDaoImpl = null;
    private static StenographyDaoImpl stenographyDaoImpl = null;
    private static ModuleDaoImpl moduleDaoImpl = null;
    private static UserModuleDaoImpl userModuleDaoImpl = null;
    private static ObjectDaoImpl objectDaoImpl = null;
    private static OperatorDaoImpl operatorDaoImpl = null;
//    private static ObjectModuleDaoImpl objectModuleDaoImpl = null;
//    private static PermissionDaoImpl permissionDaoImpl = null;
    private static RolePermissionDaoImpl rolePermissionDaoImpl = null;
    private static RoleModuleDaoImpl roleModuleDaoImpl = null;
    private static UserRoleDaoImpl userRoleDaoImpl = null;
    private static ExtendPermissionUserDaoImpl extendPermissionUserDaoImpl = null;
    private static NotificationDaoImpl notificationDaoImpl = null;

    public static UserDaoImpl getUserDaoInstance() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }
    public static DictStenoDaoImpl getDictStenoDaoInstance(){
        if(dictStenoDaoImpl == null){
            dictStenoDaoImpl = new DictStenoDaoImpl();
        }
        return dictStenoDaoImpl;
    }
    public static RoleDaoImpl getRoleDaoImpl(){
        if(roleDaoImpl == null){
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }
    public static SpeakerDaoImpl getSpeakerDaoInstance(){
        if(speakerDaoImpl == null){
            speakerDaoImpl = new SpeakerDaoImpl();
        }
        return speakerDaoImpl;
    }
    public static AudioSampleDaoImpl getAudioSampleDaoInstance(){
        if(audioSampleDaoImpl == null){
            audioSampleDaoImpl = new AudioSampleDaoImpl();
        }
        return audioSampleDaoImpl;
    }

    public static MeetingDaoImpl getMeetingDaoInstance(){
        if(meetingDaoImpl == null){
            meetingDaoImpl = new MeetingDaoImpl();
        }
        return meetingDaoImpl;
    }

    public static SessionDaoImpl getSessionDaoInstance(){
        if(sessionDaoImpl == null){
            sessionDaoImpl = new SessionDaoImpl();
        }
        return sessionDaoImpl;
    }

    public static DutyDaoImpl getDutyDaoInstance(){
        if(dutyDaoImpl == null){
            dutyDaoImpl = new DutyDaoImpl();
        }
        return dutyDaoImpl;
    }

    public static AttendeesDaoImpl getAttendeesDaoInstance(){
        if(attendeesDaoImpl == null){
            attendeesDaoImpl = new AttendeesDaoImpl();
        }
        return attendeesDaoImpl;
    }

    public static TemplateDaoImpl getTemplateDaoInstance(){
        if(templateDaoImpl == null){
            templateDaoImpl = new TemplateDaoImpl();
        }
        return templateDaoImpl;
    }

    public static ReportDaoImpl getReportDaoInstance(){
        if(reportDaoImpl == null){
            reportDaoImpl = new ReportDaoImpl();
        }
        return reportDaoImpl;
    }

    public static RoomDaoImpl getRoomDaoInstance(){
        if(roomDaoImpl == null){
            roomDaoImpl = new RoomDaoImpl();
        }
        return roomDaoImpl;
    }

    public static EquipmentDaoImpl getEquipmentDaoInstance(){
        if(equipmentDaoImpl == null){
            equipmentDaoImpl = new EquipmentDaoImpl();
        }
        return equipmentDaoImpl;
    }


    public static RecordDaoImpl getRecordDaoInstance(){
        if(recordDaoImpl == null){
            recordDaoImpl = new RecordDaoImpl();
        }
        return recordDaoImpl;
    }

    public static TranscriptDaoImpl getTranscriptDaoInstance(){
        if(transcriptDaoImpl == null){
            transcriptDaoImpl = new TranscriptDaoImpl();
        }
        return transcriptDaoImpl;
    }

    public static ConfigDaoImpl getConfigDaoInstance(){
        if(configDaoImpl == null){
            configDaoImpl = new ConfigDaoImpl();
        }
        return configDaoImpl;
    }

    public static StenographyDaoImpl getStenographyDaoImpl(){
        if (stenographyDaoImpl == null){
            stenographyDaoImpl = new StenographyDaoImpl();
        }
        return stenographyDaoImpl;
    }

    public static ModuleDaoImpl getModuleDaoInstance(){
        if (moduleDaoImpl == null){
            moduleDaoImpl = new ModuleDaoImpl();
        }
        return moduleDaoImpl;
    }

    public static UserModuleDaoImpl getUserModuleDaoInstance(){
        if (userModuleDaoImpl == null){
            userModuleDaoImpl = new UserModuleDaoImpl();
        }
        return userModuleDaoImpl;
    }

    public static ObjectDaoImpl getObjectDaoInstance(){
        if (objectDaoImpl == null){
            objectDaoImpl = new ObjectDaoImpl();
        }
        return objectDaoImpl;
    }

    public static OperatorDaoImpl getOperatorDaoInstance(){
        if (operatorDaoImpl == null){
            operatorDaoImpl = new OperatorDaoImpl();
        }
        return operatorDaoImpl;
    }

//    public static ObjectModuleDaoImpl getObjectModuleDaoInstance(){
//        if (objectModuleDaoImpl == null){
//            objectModuleDaoImpl = new ObjectModuleDaoImpl();
//        }
//        return objectModuleDaoImpl;
//    }

//    public static PermissionDaoImpl getPermissionDaoInstance(){
//        if(permissionDaoImpl == null){
//            permissionDaoImpl = new PermissionDaoImpl();
//        }
//        return permissionDaoImpl;
//    }

    public static RolePermissionDaoImpl getRolePermissionDaoInstance(){
        if(rolePermissionDaoImpl == null){
            rolePermissionDaoImpl = new RolePermissionDaoImpl();
        }
        return rolePermissionDaoImpl;
    }

    public static RoleModuleDaoImpl getRoleModuleDaoInstance(){
        if(roleModuleDaoImpl == null){
            roleModuleDaoImpl = new RoleModuleDaoImpl();
        }
        return roleModuleDaoImpl;
    }

    public static UserRoleDaoImpl getUserRoleDaoInstance(){
        if(userRoleDaoImpl == null){
            userRoleDaoImpl = new UserRoleDaoImpl();
        }
        return userRoleDaoImpl;
    }

    public static ExtendPermissionUserDaoImpl getExtendPermissionUserDaoInstance(){
        if(extendPermissionUserDaoImpl == null){
            extendPermissionUserDaoImpl = new ExtendPermissionUserDaoImpl();
        }
        return extendPermissionUserDaoImpl;
    }

    public static NotificationDaoImpl getNotificationDaoInstance(){
        if(notificationDaoImpl == null){
            notificationDaoImpl = new NotificationDaoImpl();
        }
        return notificationDaoImpl;
    }

}
