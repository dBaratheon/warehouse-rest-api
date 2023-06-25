package miniproject.warehouse.service;

import miniproject.warehouse.entity.InventoryWarehouse;

import java.util.List;

public interface InventoryWarehouseService {
    List<InventoryWarehouse> findByWarehouseId(String warehouseId);
    List<InventoryWarehouse> findByGoodsId(String goodsId);
    List<InventoryWarehouse> findAllInventoryWarehouse();
}
