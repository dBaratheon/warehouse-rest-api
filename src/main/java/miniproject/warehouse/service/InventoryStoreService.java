package miniproject.warehouse.service;

import miniproject.warehouse.entity.InventoryStore;

import java.util.List;

public interface InventoryStoreService {
    List<InventoryStore> findAll();
    List<InventoryStore> findByStoreId(String storeId);
    List<InventoryStore> findByGoodsId(String goodsId);
}
