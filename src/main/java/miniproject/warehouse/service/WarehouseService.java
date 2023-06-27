package miniproject.warehouse.service;

import miniproject.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WarehouseService {
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse findByWarehouseId(String warehouseId);
    Page<Warehouse> findAllRecord(int pageNo, int pageSize);
    Warehouse updateWarehouse(String warehouseId, Warehouse warehouse);
    String deleteWarehouse(String warehouseId);
}
