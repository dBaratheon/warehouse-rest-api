package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.entity.Store;

import java.util.List;

public interface InventoryStoreService {
    List<InventoryStore> findAll();
    List<InventoryStore> findByStoreId(String storeId);
    List<InventoryStore> findByGoodsId(String goodsId);
}
