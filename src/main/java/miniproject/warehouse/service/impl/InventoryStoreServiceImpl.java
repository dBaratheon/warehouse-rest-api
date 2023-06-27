package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryStoreRepository;
import miniproject.warehouse.service.InventoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryStoreServiceImpl implements InventoryStoreService {
    @Autowired
    private InventoryStoreRepository inventoryStoreRepository;

    @Override
    public Page<InventoryStore> findAllRecord(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return inventoryStoreRepository.findAll(pageable);
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
