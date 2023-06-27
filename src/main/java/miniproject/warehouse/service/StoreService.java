package miniproject.warehouse.service;

import miniproject.warehouse.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreService {
    Store createStore(Store store);
    Store findByStoreId(String storeId);
    Page<Store> findAllRecord(int pageNo, int pageSize);
    Store updateStore(String storeId, Store store);
    String deleteStore(String warehouseId);
}
