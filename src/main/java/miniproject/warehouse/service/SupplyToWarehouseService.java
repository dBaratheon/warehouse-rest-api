package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.SupplyToWarehouse;
import miniproject.warehouse.entity.Warehouse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplyToWarehouseService {
    ResponseEntity<SupplyToWarehouse> supplyToWarehouse(Warehouse warehouseDestinationId, Goods goodsId, SupplyToWarehouse supplyToWarehouse);
    List<SupplyToWarehouse> findAll();
}
