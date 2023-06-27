package miniproject.warehouse.service;

import miniproject.warehouse.entity.InventoryWarehouse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryWarehouseService {
    List<InventoryWarehouse> findByWarehouseId(String warehouseId);
    List<InventoryWarehouse> findByGoodsId(String goodsId);
    Page<InventoryWarehouse> findAllRecord(int pageNo, int pageSize);
}
