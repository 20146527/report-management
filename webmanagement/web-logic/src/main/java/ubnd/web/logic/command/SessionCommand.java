package ubnd.web.logic.command;

import ubnd.core.dto.SessionDto;
import ubnd.core.dto.SessionImportDto;
import ubnd.core.web.command.AbstractCommand;

import java.util.List;


public class SessionCommand extends AbstractCommand<SessionDto> {
    public SessionCommand() {
        this.pojo = new SessionDto();
    }
    private Integer meetingId;
    private Integer meetingSearchId;
    private String timeStart;
    private String timeEnd;
    private Integer roomId;
    private List<SessionImportDto> sessionImportDtoList;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getMeetingSearchId() {
        return meetingSearchId;
    }

    public void setMeetingSearchId(Integer meetingSearchId) {
        this.meetingSearchId = meetingSearchId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public List<SessionImportDto> getSessionImportDtoList() {
        return sessionImportDtoList;
    }

    public void setSessionImportDtoList(List<SessionImportDto> sessionImportDtoList) {
        this.sessionImportDtoList = sessionImportDtoList;
    }
}
