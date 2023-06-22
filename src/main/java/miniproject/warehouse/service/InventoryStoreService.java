package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.entity.Store;

import java.util.List;

public interface InventoryStoreService {
    List<InventoryStore> findByStoreId(String storeId);
    List<InventoryStore> findByStoreIdAndGoodsId(Store storeId, Goods goodsId);
    List<InventoryStore> findAllInventoryStore();
}
