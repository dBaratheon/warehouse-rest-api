package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.entity.Store;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryStoreRepository;
import miniproject.warehouse.service.InventoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class InventoryStoreServiceImpl implements InventoryStoreService {
    @Autowired
    private InventoryStoreRepository inventoryStoreRepository;

    @Override
    public List<InventoryStore> findAll() {
        List<InventoryStore> inventoryStoreList = inventoryStoreRepository.findAll();
        if (inventoryStoreList.isEmpty()){
            throw new NotFoundException("Inventory is empty");
        }
        return inventoryStoreList;
    }

    @Override
    public List<InventoryStore> findByStoreId(String storeId) {
            List<InventoryStore> inventoryStoreList = inventoryStoreRepository.findAllByStoreId(storeId);
            if (inventoryStoreList.isEmpty()){
                throw new NotFoundException("Store id not found");
            }
            return inventoryStoreList;
    }

    @Override
    public List<InventoryStore> findByGoodsId(String goodsId) {
        List<InventoryStore> inventoryStoreList = inventoryStoreRepository.findAllByGoodsId(goodsId);
        if (inventoryStoreList.isEmpty()){
            throw new NotFoundException("Goods id not found");
        }
        return inventoryStoreList;
    }
}
