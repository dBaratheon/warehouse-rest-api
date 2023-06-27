package miniproject.warehouse.service;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.SupplyToWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplyToWarehouseService {
    ResponseEntity<SupplyToWarehouse> supplyToWarehouse(TransferDto transferDto);
    Page<SupplyToWarehouse> findAllRecord(int pageNo, int pageSize);
}