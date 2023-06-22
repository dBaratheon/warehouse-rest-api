package miniproject.warehouse.service;

import miniproject.warehouse.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse findByWarehouseId(String warehouseId);
    List<Warehouse> findAllWarehouse();
    Warehouse updateWarehouse(String warehouseId, Warehouse warehouse);
    String deleteWarehouse(String warehouseId);

}
