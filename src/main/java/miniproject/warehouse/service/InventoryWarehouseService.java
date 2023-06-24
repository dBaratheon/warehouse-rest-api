package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.Warehouse;

import java.util.List;

public interface InventoryWarehouseService {
    List<InventoryWarehouse> findByWarehouseId(String warehouseId);
    List<InventoryWarehouse> findByGoodsId(String goodsId);
    List<InventoryWarehouse> findAllInventoryWarehouse();
}
