package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.repository.TransferToAnotherWarehouseRepository;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.TransferToAnotherWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TransferToAnotherWarehouseServiceImpl implements TransferToAnotherWarehouseService {
    @Autowired
    TransferToAnotherWarehouseRepository transferToAnotherWarehouseRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    InventoryWarehouseRepository inventoryWarehouseRepository;

    @Override
    public ResponseEntity<TransferToAnotherWarehouse> transfer(Warehouse warehouseSrc, Warehouse warehouseDst, Goods goods, TransferToAnotherWarehouse transfer) {
        TransferToAnotherWarehouse transferToAnotherWarehouse = warehouseRepository.findById(warehouseSrc.getId()).map(warehouse -> {
            InventoryWarehouse inventoryWarehouseSrc = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goods);
            InventoryWarehouse inventoryWarehouseDst = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseDst, goods);
            InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
            TransferToAnotherWarehouse warehouseToWarehouse = new TransferToAnotherWarehouse();
            if (inventoryWarehouseSrc.getQuantity() >= transfer.getQuantity()){
                inventoryWarehouseSrc.setQuantity(inventoryWarehouseSrc.getQuantity() - transfer.getQuantity());
                try {
                    inventoryWarehouseDst.setQuantity(inventoryWarehouseDst.getQuantity()+transfer.getQuantity());
                    inventoryWarehouseRepository.save(inventoryWarehouseDst);
                }catch (Exception e){
                    inventoryWarehouse.setGoods(goods);
                    inventoryWarehouse.setWarehouse(warehouseDst);
                    inventoryWarehouse.setQuantity(transfer.getQuantity());
                    inventoryWarehouseRepository.save(inventoryWarehouse);
                }
                warehouseToWarehouse.setId("WTW" + (transferToAnotherWarehouseRepository.count()));
                warehouseToWarehouse.setWarehouseSrc(warehouseSrc);
                warehouseToWarehouse.setWarehouseDst(warehouseDst);
                warehouseToWarehouse.setGoods(goods);
                warehouseToWarehouse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            }
            return transferToAnotherWarehouseRepository.save(warehouseToWarehouse);
        }).orElseThrow(() -> new NotFoundException("Warehouse not found"));
        return new ResponseEntity<>(transferToAnotherWarehouse, HttpStatus.CREATED);
    }
}
