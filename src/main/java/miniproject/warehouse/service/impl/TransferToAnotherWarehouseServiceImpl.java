package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.BadRequestException;
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
import java.util.List;

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
            if (inventoryWarehouseSrc.getQuantity() < transfer.getQuantity()){
                throw new BadRequestException("Your goods quantity isn't enough");
            } else {
                inventoryWarehouseSrc.setQuantity(inventoryWarehouseSrc.getQuantity() - transfer.getQuantity());
                try {
                    inventoryWarehouseDst.setQuantity(inventoryWarehouseDst.getQuantity()+transfer.getQuantity());
                    inventoryWarehouseRepository.save(inventoryWarehouseDst);
                }catch (Exception e){
                    inventoryWarehouse.setGoods(goods);
                    inventoryWarehouse.setWarehouse(warehouseDst);
                    inventoryWarehouse.setQuantity(transfer.getQuantity());
                    inventoryWarehouse.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
                    inventoryWarehouseRepository.save(inventoryWarehouse);
                }
                warehouseToWarehouse.setWarehouseSrc(warehouseSrc);
                warehouseToWarehouse.setWarehouseDst(warehouseDst);
                warehouseToWarehouse.setGoods(goods);
                warehouseToWarehouse.setQuantity(transfer.getQuantity());
                warehouseToWarehouse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            }
            return transferToAnotherWarehouseRepository.save(warehouseToWarehouse);
        }).orElseThrow(() -> new NotFoundException("warehouse id or goods id not found"));
        return new ResponseEntity<>(transferToAnotherWarehouse, HttpStatus.CREATED);
    }

    @Override
    public List<TransferToAnotherWarehouse> findAll() {
        List<TransferToAnotherWarehouse> transferToAnotherWarehouseList = transferToAnotherWarehouseRepository.findAll();
        if (transferToAnotherWarehouseList.isEmpty()){
            throw new NotFoundException("Record is empty");
        }
        return transferToAnotherWarehouseList;
    }
}
