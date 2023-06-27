package miniproject.warehouse.service.impl;

import miniproject.warehouse.dto.TransferDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplyToWarehouseServiceImpl implements SupplyToWarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SupplyToWarehouseRepository supplyToWarehouseRepository;

    @Autowired
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @Override
    @Transactional
    public ResponseEntity<SupplyToWarehouse> supplyToWarehouse(TransferDto transferDto) {
        Warehouse warehouse = warehouseRepository.findById(transferDto.getWarehouseDst())
                .orElseThrow(() -> new NotFoundException("Warehouse not found"));

        Goods goods = goodsRepository.findById(transferDto.getGoods())
                .orElseThrow(() -> new NotFoundException("Goods not found"));

        SupplyToWarehouse supplyToWarehouse = new SupplyToWarehouse();
        supplyToWarehouse.setWarehouse(warehouse);
        supplyToWarehouse.setGoods(goods);
        supplyToWarehouse.setQuantity(transferDto.getQuantity());
        supplyToWarehouse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        try {
            InventoryWarehouse inventoryWarehouseUpdate = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouse, goods);
            inventoryWarehouseUpdate.setQuantity(inventoryWarehouseUpdate.getQuantity() + transferDto.getQuantity());
            inventoryWarehouseRepository.save(inventoryWarehouseUpdate);
        } catch (Exception ex) {
            InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
            inventoryWarehouse.setWarehouse(warehouse);
            inventoryWarehouse.setGoods(goods);
            inventoryWarehouse.setQuantity(transferDto.getQuantity());
            inventoryWarehouse.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
            inventoryWarehouseRepository.save(inventoryWarehouse);
        }

        SupplyToWarehouse savedSupply = supplyToWarehouseRepository.save(supplyToWarehouse);
        return new ResponseEntity<>(savedSupply, HttpStatus.CREATED);
    }

    @Override
    public Page<SupplyToWarehouse> findAllRecord(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return supplyToWarehouseRepository.findAll(pageable);    
    }
}
