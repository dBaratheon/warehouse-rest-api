package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.Store;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.entity.WarehouseToStore;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WarehouseToStoreService {
    ResponseEntity<WarehouseToStore> transferToStore(Warehouse warehouseSrc, Store storeDst, Goods goods, WarehouseToStore warehouseToStore);
    List<WarehouseToStore> findAll();
}
