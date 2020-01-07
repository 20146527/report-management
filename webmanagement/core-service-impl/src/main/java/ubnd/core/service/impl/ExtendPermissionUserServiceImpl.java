package ubnd.core.service.impl;

import ubnd.common.utils.TimeUtils;
import ubnd.core.dto.*;
import ubnd.core.persistence.data.entity.ExtendPermissionUserEntity;
import ubnd.core.service.ExtendPermissionUserService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ExtendPermissionUserBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExtendPermissionUserServiceImpl implements ExtendPermissionUserService {

    @Override
    public List<ExtendPermissionUserDto> findAll() {
        List<ExtendPermissionUserEntity> entities = SingletonDaoUtil.getExtendPermissionUserDaoInstance().findAll();
        List<ExtendPermissionUserDto> dtos = new ArrayList<>();
        for(ExtendPermissionUserEntity item: entities){
            ExtendPermissionUserDto dto = ExtendPermissionUserBeanUtils.entityToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ExtendPermissionUserDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getExtendPermissionUserDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<ExtendPermissionUserDto> dtos = new ArrayList<>();
        for(ExtendPermissionUserEntity item: (List<ExtendPermissionUserEntity>)objects[1]){
            ExtendPermissionUserDto dto = ExtendPermissionUserBeanUtils.entityToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    private ExtendPermissionUserDto findExtendPermissionUserByID(Integer extendPermissionUserId) {
        ExtendPermissionUserEntity entity = SingletonDaoUtil.getExtendPermissionUserDaoInstance().findByID(extendPermissionUserId);
        return ExtendPermissionUserBeanUtils.entityToDto(entity);
    }

    private void updateExtendPermissionUser(ExtendPermissionUserDto extendPermissionUserDto) {
        ExtendPermissionUserEntity entity = ExtendPermissionUserBeanUtils.dtoToEntity(extendPermissionUserDto);
        SingletonDaoUtil.getExtendPermissionUserDaoInstance().update(entity);
    }

    private void saveExtendPermissionUser(ExtendPermissionUserDto extendPermissionUserDto){
        ExtendPermissionUserEntity entity = ExtendPermissionUserBeanUtils.dtoToEntity(extendPermissionUserDto);
        SingletonDaoUtil.getExtendPermissionUserDaoInstance().save(entity);
    }

    private void deletePermission(Integer extendPermissionUserId) {
        List<Integer> list = new ArrayList<>();
        list.add(extendPermissionUserId);
        SingletonDaoUtil.getExtendPermissionUserDaoInstance().delete(list);
    }

    @Override
    public void acceptPermission(Integer extendPermissionUserId, Integer count, Integer operatorId, UserDto userDto) {
        ExtendPermissionUserDto dto = findExtendPermissionUserByID(extendPermissionUserId);
        OperatorDto operatorDto = new OperatorDto();
        operatorDto.setOperatorID(operatorId);
        dto.setOperatorDto(operatorDto);
        dto.setRequestTag(0);
        dto.setCount(count);
        dto.setCreUID(userDto.getUserId());
        dto.setCreDate(TimeUtils.getCurrentTimeStamp());
        updateExtendPermissionUser(dto);
        //create notify
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setUserId(dto.getUserDto().getUserId());
        notificationDto.setTitle("Đồng ý quyền");
        notificationDto.setContent(dto.getObjectDto().getNameObject() + " :" + dto.getObjectDto().getDescription());
        notificationDto.setLink("/ajax-permission.html?type=updatePermission");
        notificationDto.setType(52);
        notificationDto.setLinkId(1);
        notificationDto.setCreDate(TimeUtils.getCurrentTimeStamp());
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        notificationService.createNotify(notificationDto);
    }

    @Override
    public void rejectPermission(Integer extendPermissionUserId) {
        ExtendPermissionUserDto dto = findExtendPermissionUserByID(extendPermissionUserId);
        //create notify
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setUserId(dto.getUserDto().getUserId());
        notificationDto.setTitle("Từ chối quyền");
        notificationDto.setContent(dto.getObjectDto().getNameObject() + " :" + dto.getObjectDto().getDescription());
        notificationDto.setLink("/ajax-permission.html?type=updatePermission");
        notificationDto.setType(53);
        notificationDto.setLinkId(1);
        notificationDto.setCreDate(TimeUtils.getCurrentTimeStamp());
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        notificationService.createNotify(notificationDto);
        //delete extendPermission
        deletePermission(extendPermissionUserId);
    }

    @Override
    public void requestExtendPermissionUser(UserDto userDto, ObjectDto objectDto) {
        ExtendPermissionUserDto dto = new ExtendPermissionUserDto();

        OperatorDto operatorDto = new OperatorDto();
        operatorDto.setOperatorID(1);

        dto.setUserDto(userDto);
        dto.setObjectDto(objectDto);
        dto.setOperatorDto(operatorDto);
        dto.setCount(1);
        dto.setRequestTag(1);
        dto.setCreUID(userDto.getUserId());
        dto.setCreDate(TimeUtils.getCurrentTimeStamp());
        saveExtendPermissionUser(dto);
        //create notify
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        notificationService.createRequestPermissionNotify(userDto, objectDto);

    }

    @Override
    public void cancelExtendPermissionUser(Integer extendPermissionUserId) {
        ExtendPermissionUserDto dto = findExtendPermissionUserByID(extendPermissionUserId);
        dto.setRequestTag(2);
        updateExtendPermissionUser(dto);
    }

}
