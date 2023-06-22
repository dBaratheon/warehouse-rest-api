package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryWarehouseRepository extends JpaRepository<InventoryWarehouse, Long> {
    List<InventoryWarehouse> findByWarehouseId(String warehouseId);
    InventoryWarehouse findByWarehouseIdAndGoodsId(Warehouse warehouseId, Goods goodsId);
}
