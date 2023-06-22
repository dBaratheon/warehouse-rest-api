package miniproject.warehouse.service;

import miniproject.warehouse.entity.Store;

import java.util.List;

public interface StoreService {
    Store createStore(Store store);
    Store findByStoreId(String storeId);
    List<Store> findAllStore();
    Store updateStore(String storeId, Store store);
    String deleteStore(String warehouseId);
}
