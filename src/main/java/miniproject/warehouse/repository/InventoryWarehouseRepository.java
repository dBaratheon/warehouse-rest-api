package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryWarehouseRepository extends JpaRepository<InventoryWarehouse, String> {

    Page<InventoryWarehouse> findAll(Pageable pageable);
    List<InventoryWarehouse> findAllByWarehouseId(String warehouseId);
    List<InventoryWarehouse> findAllByGoodsId(String goodsId);
    InventoryWarehouse findByWarehouseAndGoods(Warehouse warehouseId, Goods goodsId);
}
