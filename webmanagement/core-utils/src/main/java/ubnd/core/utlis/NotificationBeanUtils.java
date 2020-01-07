package ubnd.core.utlis;

import ubnd.core.dto.NotificationDto;
import ubnd.core.persistence.data.entity.NotificationEntity;

public class NotificationBeanUtils {
    public static NotificationEntity dtoToEntity(NotificationDto dto){
        NotificationEntity entity = new NotificationEntity();
        entity.setNotificationId(dto.getNotificationId());
        entity.setUserId(dto.getUserId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setLink(dto.getLink());
        entity.setType(dto.getType());
        entity.setLinkId(dto.getLinkId());
        entity.setCreDate(dto.getCreDate());
        return entity;
    }
    public static NotificationDto entityToDTO(NotificationEntity entity){
        NotificationDto dto = new NotificationDto();
        dto.setNotificationId(entity.getNotificationId());
        dto.setUserId(entity.getUserId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setLink(entity.getLink());
        dto.setType(entity.getType());
        dto.setLinkId(entity.getLinkId());
        dto.setCreDate(entity.getCreDate());
        return dto;
    }


}
