package miniproject.warehouse.repository;

import miniproject.warehouse.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryStoreRepository extends JpaRepository<InventoryStore, String> {
    InventoryStore findFirstByStoreAndGoods(Store store, Goods goods);

    List<InventoryStore> findAllByStoreId(String storeId);

    List<InventoryStore> findAllByGoodsId(String goodsId);

    Page<InventoryStore> findAll(Pageable pageable);
}
