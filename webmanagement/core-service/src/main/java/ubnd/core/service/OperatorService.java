package ubnd.core.service;

import ubnd.core.dto.OperatorDto;

import java.util.List;

public interface OperatorService {
    List<OperatorDto> findAll();
    OperatorDto findByID(Integer id);
}
