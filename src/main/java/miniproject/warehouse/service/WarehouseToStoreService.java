package miniproject.warehouse.service;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.Store;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.entity.WarehouseToStore;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WarehouseToStoreService {
    ResponseEntity<WarehouseToStore> transferToStore(TransferDto transferDto);

    Page<WarehouseToStore> findAllRecord(int pageNo, int pageSize);
}
