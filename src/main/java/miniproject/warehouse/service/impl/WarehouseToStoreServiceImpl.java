package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.*;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.*;
import miniproject.warehouse.service.WarehouseToStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarehouseToStoreServiceImpl implements WarehouseToStoreService {
    @Autowired
    private WarehouseToStoreRepository warehouseToStoreRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private InventoryStoreRepository inventoryStoreRepository;
    @Autowired
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @Override
    public ResponseEntity<WarehouseToStore> transferToStore(Warehouse warehouseSrc, Store storeDst, Goods goodsId, WarehouseToStore warehouse) {
        WarehouseToStore warehouseToStore = warehouseRepository.findById(warehouseSrc.getId()).map(w -> {
            InventoryStore newInventoryStore = new InventoryStore();
            InventoryStore inventoryStore = inventoryStoreRepository.findFirstByStoreAndGoods(storeDst, goodsId);
            InventoryWarehouse inventoryWarehouse = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goodsId);
            WarehouseToStore wts = new WarehouseToStore();

            if (inventoryWarehouse.getQuantity() < warehouse.getQuantity()){
                throw new BadRequestException("Quantity isn't enough");
            }
            else {
                try {
                    inventoryStore.setQuantity(inventoryStore.getQuantity() + warehouse.getQuantity());
                    inventoryWarehouse.setQuantity(inventoryStore.getQuantity() - warehouse.getQuantity());
                    inventoryStoreRepository.save(inventoryStore);
                    inventoryWarehouseRepository.save(inventoryWarehouse);
                }catch (Exception e){
                    newInventoryStore.setStore(storeDst);
                    newInventoryStore.setGoods(goodsId);
                    newInventoryStore.setQuantity(warehouse.getQuantity());
                    newInventoryStore.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
                    inventoryWarehouse.setQuantity(inventoryWarehouse.getQuantity() - warehouse.getQuantity());
                    inventoryWarehouseRepository.save(inventoryWarehouse);
                    inventoryStoreRepository.save(newInventoryStore);
                }
                inventoryWarehouseRepository.save(inventoryWarehouse);
                wts.setWarehouseSrc(warehouseSrc);
                wts.setStoreDst(storeDst);
                wts.setGoods(goodsId);
                wts.setQuantity(warehouse.getQuantity());
                wts.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            }
            return warehouseToStoreRepository.save(wts);
        }).orElseThrow(() -> new NotFoundException("Warehouse not found"));
        return new ResponseEntity<>(warehouseToStore, HttpStatus.CREATED);
    }

    @Override
    public List<WarehouseToStore> findAll() {
        return warehouseToStoreRepository.findAll();
    }
}
