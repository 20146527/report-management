package ubnd.core.service.impl;

import ubnd.core.dao.UserDao;
import ubnd.core.dao.impl.UserDaoImpl;
import ubnd.core.dto.UserDto;
import ubnd.core.persistence.data.entity.UserEntity;
import ubnd.core.service.UserService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.StringUtils;
import ubnd.core.utlis.UserBeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {
    @Override
    public List<UserDto> findAll() {
        List<UserEntity> list = SingletonDaoUtil.getUserDaoInstance().findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserEntity entity : list){
            if (!entity.getStatus().equals(1)){
                userDtoList.add(UserBeanUtils.entityToDTO(entity));
            }
        }
        return userDtoList;
    }

    public UserDto isUserExist(UserDto dto) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().isUserExist(dto.getUserName(), StringUtils.stringToSHA256(dto.getPassWord()));
        return UserBeanUtils.entityToDTO(entity);
    }

    public void updateUser(UserDto userDto){
        SingletonDaoUtil.getUserDaoInstance().update(UserBeanUtils.dtoToEntity(userDto));
    }

    public void saveUser(UserDto userDto){
        UserEntity entity = UserBeanUtils.dtoToEntity(userDto);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    public UserDto updateUserInfo(UserDto dto) {
        UserDao dao = new UserDaoImpl();
        UserEntity entity = dao.updateInfoUser(dto.getUserName(), StringUtils.stringToSHA256(dto.getPassWord()));
        return UserBeanUtils.entityToDTO(entity);
    }

    /**
     *
     * @param property: cac truong tim kiem
     * @param sortExpression: gioi
     * @param sortDirection
     * @param offset
     * @param limit
     * @return danh sach
     */
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        for (UserEntity item: (List<UserEntity>)objects[1]){
            UserDto userDto = UserBeanUtils.entityToDTO(item);
            userDtoList.add(userDto);
        }
        objects[1] = userDtoList;
        return objects;
    }

    public UserDto findById(Integer userId){
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findByID(userId);
        return UserBeanUtils.entityToDTO(entity);
    }

    public int delete(Integer userId){
        return SingletonDaoUtil.getUserDaoInstance().status(userId);
    }
}
