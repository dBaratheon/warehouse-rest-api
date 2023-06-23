package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.entity.Warehouse;
import org.springframework.http.ResponseEntity;

public interface TransferToAnotherWarehouseService {
    ResponseEntity<TransferToAnotherWarehouse> transfer(Warehouse warehouseSrc, Warehouse warehouseDst, Goods goods, TransferToAnotherWarehouse transfer);
}
