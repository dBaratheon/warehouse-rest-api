package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.entity.Store;
import miniproject.warehouse.repository.InventoryStoreRepository;
import miniproject.warehouse.service.InventoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryStoreServiceImpl implements InventoryStoreService {
    @Autowired
    private InventoryStoreRepository inventoryStoreRepository;

    @Override
    public List<InventoryStore> findByStoreId(String storeId) {
        return inventoryStoreRepository.findByStoreId(storeId);
    }

    @Override
    public List<InventoryStore> findByStoreIdAndGoodsId(Store storeId, Goods goodsId) {
        return inventoryStoreRepository.findByStoreIdAndGoodsId(storeId, goodsId);
    }

    @Override
    public List<InventoryStore> findAllInventoryStore() {
        return inventoryStoreRepository.findAll();
    }
}
