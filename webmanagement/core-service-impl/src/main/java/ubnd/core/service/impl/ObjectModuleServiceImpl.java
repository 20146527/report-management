//package ubnd.core.service.impl;
//
//import ubnd.core.dao.impl.ObjectModuleDaoImpl;
//import ubnd.core.dto.ObjectModuleDto;
//import ubnd.core.persistence.data.entity.ObjectModuleEntity;
//import ubnd.core.service.ObjectModuleService;
//import ubnd.core.service.ultis.SingletonDaoUtil;
//import ubnd.core.utlis.ObjectModuleBeanUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class ObjectModuleServiceImpl implements ObjectModuleService {
//    @Override
//    public List<ObjectModuleDto> findAll() {
//        List<ObjectModuleDto> list = new ArrayList<>();
//        List<ObjectModuleEntity> entityList = SingletonDaoUtil.getObjectModuleDaoInstance().findAll();
//        for (ObjectModuleEntity entity : entityList){
//            list.add(ObjectModuleBeanUtils.entityToDto(entity));
//        }
//        return list;
//    }
//
//    @Override
//    public List<ObjectModuleDto> findByModuleID(Integer id) {
//        List<ObjectModuleDto> list = new ArrayList<>();
//        List<ObjectModuleEntity> entityList = SingletonDaoUtil.getObjectModuleDaoInstance().findByModuleID(id);
//        for (ObjectModuleEntity entity : entityList){
//            list.add(ObjectModuleBeanUtils.entityToDto(entity));
//        }
//        return list;
//    }
//
//    @Override
//    public void save(ObjectModuleDto dto) {
//        SingletonDaoUtil.getObjectModuleDaoInstance().save(ObjectModuleBeanUtils.dtoToEntity(dto));
//    }
//
//    @Override
//    public void delete(Integer id) {
//        List<Integer> list = new ArrayList<>();
//        list.add(id);
//        SingletonDaoUtil.getObjectModuleDaoInstance().delete(list);
//    }
//
//    @Override
//    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
//        return SingletonDaoUtil.getObjectModuleDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
//
//    }
//}
