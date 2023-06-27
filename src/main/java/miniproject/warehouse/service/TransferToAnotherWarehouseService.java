package miniproject.warehouse.service;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransferToAnotherWarehouseService {
    ResponseEntity<TransferToAnotherWarehouse> transfer(TransferDto transferRequest);
    Page<TransferToAnotherWarehouse> findAllRecord(int pageNo, int pageSize);
}
