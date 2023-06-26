package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.SupplyToWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.repository.SupplyToWarehouseRepository;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.SupplyToWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplyToWarehouseServiceImpl implements SupplyToWarehouseService {

    @Autowired
    private SupplyToWarehouseRepository supplyToWarehouseRepository;
    @Autowired
    private InventoryWarehouseRepository inventoryWarehouseRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public ResponseEntity<SupplyToWarehouse> supplyToWarehouse(Warehouse warehouseId, Goods goodsId, SupplyToWarehouse supplyToWarehouse) {
        SupplyToWarehouse supply = warehouseRepository.findById(warehouseId.getId()).map(warehouse -> {
            Goods goods = goodsRepository.findById(goodsId.getId()).get();
            supplyToWarehouse.setWarehouse(warehouse);
            supplyToWarehouse.setGoods(goods);
            supplyToWarehouse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
            try {
                InventoryWarehouse inventoryWarehouseUpdate = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseId,goodsId);
                inventoryWarehouseUpdate.setQuantity(inventoryWarehouseUpdate.getQuantity() + supplyToWarehouse.getQuantity());
                inventoryWarehouseRepository.save(inventoryWarehouseUpdate);
            } catch (Exception ex){
                inventoryWarehouse.setGoods(goods);
                inventoryWarehouse.setWarehouse(warehouse);
                inventoryWarehouse.setQuantity(supplyToWarehouse.getQuantity());
                inventoryWarehouse.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
                inventoryWarehouseRepository.save(inventoryWarehouse);
            }
            return supplyToWarehouseRepository.save(supplyToWarehouse);
        }).orElseThrow(() -> new NotFoundException("Warehouse not found"));
        return new ResponseEntity<>(supply, HttpStatus.CREATED);
    }

    @Override
    public List<SupplyToWarehouse> findAll() {
        List<SupplyToWarehouse> supplyToWarehouseList = supplyToWarehouseRepository.findAll();
        if (supplyToWarehouseList.isEmpty()){
            throw new NotFoundException("Record is empty");
        }
        return  supplyToWarehouseList;
    }
}
