package ubnd.web.logic.utils;

import java.util.List;

public class CustomFunctionJAIL {
    public static boolean checkPermission(List<String> list, String s) {
        return list.contains(s);
    }

    public static boolean checkTitleMenu(List<String> list, int i) {

        switch (i) {
            case 0:
                return list.contains("/manager-record.html")
                        || list.contains("/manager-audio.html")
                        || list.contains("/manager-record.html")
                        || list.contains("/report-transcript.html");
            case 1:
                return list.contains("/manager-meeting-list.html")
                        || list.contains("/manager-session-list.html");
            case 2:
                return list.contains("/report-create.html")
                        || list.contains("/report-list.html");
            case 3:
                return list.contains("/manager-statistic-file.html");
            case 4:
                return list.contains("/history.html")
                        || list.contains("/manager-equipment.html")
                        || list.contains("")
                        || list.contains("/manager-equipment.html")
                        || list.contains("//manager-speaker-list.html")
                        || list.contains("/list-data.html")
                        || list.contains("/setting.html")
                        || list.contains("/manager-template.html");
            case 5:
                return list.contains("/typing-shorthand.html")
                        || list.contains("/steno-convert.html")
                        || list.contains("/steno-file-manager.html")
                        || list.contains("/word-manager.html")
                        || list.contains("/typing-rules.html")
                        || list.contains("/manager-dict-steno-list.html")
                        || list.contains("/manager-layout.html");
            case 6:
                return list.contains("/personal-information.html")
                        || list.contains("/manager-person-dict-steno.html")
                        || list.contains("/steno-setting-oder.html");
            case 7:
                return list.contains("/manager-extend-permission.html")
                        || list.contains("/manager-permission.html")
                        || list.contains("/manager-module.html")
                        || list.contains("/manager-user-list.html");
            case 8:
                return list.contains("/info.html")
                        || list.contains("/qna.html");
            default:
                return false;
        }

    }
}
