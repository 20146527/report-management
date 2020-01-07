package ubnd.core.service.impl;

import ubnd.core.dto.ReportDto;
import ubnd.core.persistence.data.entity.ReportEntity;
import ubnd.core.service.ReportService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.ReportBeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    @SuppressWarnings("unchecked")
    public List<ReportDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getReportDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<ReportDto> reportDtoList = new ArrayList<>();
        for (ReportEntity item : (List<ReportEntity>) objects[1]) {
            ReportDto reportDto = ReportBeanUtils.entityToDTO(item);
            reportDtoList.add(reportDto);
        }
        return reportDtoList;
    }

    public ReportDto findById(Integer id) {
        ReportEntity entity = SingletonDaoUtil.getReportDaoInstance().findByID(id);
        return ReportBeanUtils.entityToDTO(entity);
    }

    public ReportDto updateReport(ReportDto dto) {
        ReportEntity entity = ReportBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getReportDaoInstance().update(entity);
        dto = ReportBeanUtils.entityToDTO(entity);
        return dto;
    }

    public void saveReport(ReportDto dto) {
        ReportEntity entity = ReportBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getReportDaoInstance().save(entity);
    }

    @Override
    public List<ReportDto> searchReport(Integer sessionId, String name, Timestamp timeStart, Timestamp timeEnd, Integer status) {
        List<ReportEntity> entities = SingletonDaoUtil.getReportDaoInstance().searchByHQL(sessionId, name, timeStart, timeEnd, status);
        List<ReportDto> dtos = new ArrayList<>();
        for(ReportEntity item: entities){
            dtos.add(ReportBeanUtils.entityToDTO(item));
        }
        return dtos;
    }

}
