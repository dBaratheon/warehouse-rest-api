package miniproject.warehouse.service;

import miniproject.warehouse.entity.InventoryStore;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryStoreService {
    Page<InventoryStore> findAllRecord(int pageNo, int pageSize);
    List<InventoryStore> findByStoreId(String storeId);
    List<InventoryStore> findByGoodsId(String goodsId);
}
