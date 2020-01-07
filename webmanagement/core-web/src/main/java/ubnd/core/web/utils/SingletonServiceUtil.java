package ubnd.core.web.utils;

import ubnd.core.service.*;
import ubnd.core.service.impl.*;

/**
 * Created by Admin on 2/9/2017.
 */
public class SingletonServiceUtil {
    private static UserService userService = null;
    private static DictStenoService dictStenoService = null;
    private static RoleService roleService = null;
    private static SpeakerService speakerService = null;
    private static AudioSampleService audioSampleService = null;
    private static MeetingService meetingService = null;
    private static SessionService sessionService = null;
    private static AttendeesService attendeesService = null;
    private static TemplateService templateService = null;
    private static ReportService reportService = null;
    private static DutyService dutyService = null;
    private static EquipmentService equipmentService = null;
    private static RoomService roomService = null;
    private static RecordService recordService = null;
    private static TranscriptService transcriptService = null;
    private static ConfigService configService = null;
    private static StenographyService stenographyService = null;
    private static ModuleService moduleService = null;
    private static UserModuleService userModuleService = null;
    private static ObjectService objectService = null;
    private static OperatorService operatorService = null;
//    private static ObjectModuleService objectModuleService = null;
    private static PermissionService permissionService = null;
    private static RolePermissionService rolePermissionService = null;
    private static RoleModuleService roleModuleService = null;
    private static UserRoleService userRoleService = null;
    private static ExtendPermissionUserService extendPermissionUserService = null;
    private static NotificationService notificationService = null;

    public static UserService getUserServiceInstance(){
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static RoleService getRoleServiceInstance(){
        if (roleService == null){
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }

    public static DictStenoService getDictStenoServiceInstance(){
        if(dictStenoService == null){
            dictStenoService = new DictStenoServiceImpl() {
            };
        }
        return dictStenoService;
    }

    public static SpeakerService getSpeakerServiceInstance(){
        if(speakerService == null){
            speakerService = new SpeakerServiceImpl();
        }
        return speakerService;
    }

    public static AudioSampleService getAudioSampleServiceInstance(){
        if(audioSampleService == null){
            audioSampleService = new AudioSampleServiceImpl();
        }
        return audioSampleService;
    }

    public static MeetingService getMeetingServiceInstance(){
        if(meetingService == null){
            meetingService = new MeetingServiceImpl();
        }
        return meetingService;
    }

    public static SessionService getSessionServiceInstance(){
        if(sessionService == null){
            sessionService = new SessionServiceImpl();
        }
        return sessionService;
    }

    public static AttendeesService getAttendeesServiceInstance(){
        if(attendeesService == null){
            attendeesService = new AttendeesServiceImpl();
        }
        return attendeesService;
    }

    public static TemplateService getTemplateServiceInstance(){
        if (templateService == null){
            templateService = new TemplateServiceImpl();
        }
        return templateService;
    }

    public static ReportService getReportServiceInstance(){
        if(reportService == null){
            reportService = new ReportServiceImpl();
        }
        return reportService;
    }

    public static DutyService getDutyServiceInstance(){
        if(dutyService == null){
            dutyService = new DutyServiceImpl();
        }
        return dutyService;
    }

    public static EquipmentService getEquipmentServiceInstance(){
        if(equipmentService == null){
            equipmentService = new EquipmentServiceImpl();
        }
        return equipmentService;
    }

    public static RoomService getRoomServiceInstance(){
        if(roomService == null){
            roomService = new RoomServiceImpl();
        }
        return roomService;
    }

    public static RecordService getRecordServiceInstance(){
        if(recordService == null){
            recordService = new RecordServiceImpl();
        }
        return recordService;
    }

    public static TranscriptService getTranscriptServiceInstance(){
        if(transcriptService == null){
            transcriptService = new TranscriptServiceImpl();
        }
        return transcriptService;
    }

    public static ConfigService getConfigServiceInstance(){
        if(configService == null){
            configService = new ConfigServiceImpl();
        }
        return configService;
    }

    public static StenographyService getStenographyService(){
        if (stenographyService == null){
            stenographyService = new StenographyServiceImpl();
        }
        return stenographyService;
    }

    public static ModuleService getModuleService(){
        if (moduleService == null){
            moduleService = new ModuleServiceImpl();
        }
        return moduleService;
    }

    public static UserModuleService getUserModuleService(){
        if (userModuleService == null){
            userModuleService = new UserModuleServiceImpl();
        }
        return userModuleService;
    }

    public static ObjectService getObjectService(){
        if (objectService == null){
            objectService = new ObjectServiceImpl();
        }
        return objectService;
    }

    public static OperatorService getOperatorService(){
        if (operatorService == null){
            operatorService = new OperatorServiceImpl();
        }
        return operatorService;
    }

//    public static ObjectModuleService getObjectModuleService(){
//        if (objectModuleService == null){
//            objectModuleService = new ObjectModuleServiceImpl();
//        }
//        return objectModuleService;
//    }

    public static PermissionService getPermissionServiceInstance(){
        if(permissionService == null){
            permissionService = new PermissionServiceImpl();
        }
        return permissionService;
    }

    public static RolePermissionService getRolePermissionServiceInstance(){
        if(rolePermissionService == null){
            rolePermissionService = new RolePermissionServiceImpl();
        }
        return rolePermissionService;
    }

    public static RoleModuleService getRoleModuleServiceInstance(){
        if(roleModuleService == null){
            roleModuleService = new RoleModuleServiceImpl();
        }
        return roleModuleService;
    }

    public static UserRoleService getUserRoleServiceInstance(){
        if(userRoleService == null){
            userRoleService = new UserRoleServiceImpl();
        }
        return userRoleService;
    }

    public static ExtendPermissionUserService getExtendPermissionUserServiceInstance(){
        if(extendPermissionUserService == null){
            extendPermissionUserService = new ExtendPermissionUserServiceImpl();
        }
        return extendPermissionUserService;
    }

    public static NotificationService getNotificationServiceInstance(){
        if(notificationService == null){
            notificationService = new NotificationServiceImpl();
        }
        return notificationService;
    }
}
