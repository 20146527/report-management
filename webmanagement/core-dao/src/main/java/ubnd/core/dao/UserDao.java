package ubnd.core.dao;

import ubnd.core.data.dao.GenericDao;
import ubnd.core.persistence.data.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity isUserExist(String userName, String passWord);
    UserEntity updateInfoUser(String userName, String passWord);
    int status(Integer id);
}
