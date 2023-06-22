package miniproject.warehouse.repository;

import miniproject.warehouse.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryStoreRepository extends JpaRepository<InventoryStore, Long> {
    List<InventoryStore> findByStoreId(String storeId);

    List<InventoryStore> findByStoreIdAndGoodsId(Store storeId, Goods goodsId);
}
