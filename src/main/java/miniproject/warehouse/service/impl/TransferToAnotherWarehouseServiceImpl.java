package miniproject.warehouse.service.impl;

import miniproject.warehouse.dto.TransferDto;
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
    @Transactional
    public ResponseEntity<TransferToAnotherWarehouse> transfer(TransferDto transferDto) {
        Warehouse warehouseSrc = warehouseRepository.findById(transferDto.getWarehouseSrc())
                .orElseThrow(() -> new NotFoundException("Source warehouse not found"));

        Warehouse warehouseDst = warehouseRepository.findById(transferDto.getWarehouseDst())
                .orElseThrow(() -> new NotFoundException("Destination warehouse not found"));

        Goods goods = goodsRepository.findById(transferDto.getGoods())
                .orElseThrow(() -> new NotFoundException("Goods not found"));

        InventoryWarehouse srcInventory = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goods);
        InventoryWarehouse dstInventory = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseDst, goods);

        if (srcInventory == null) {
            throw new NotFoundException("Inventory not found in source warehouse");
        }

        if (dstInventory == null) {
            throw new NotFoundException("Inventory not found in destination warehouse");
        }

        if (srcInventory.getQuantity() < transferDto.getQuantity()) {
            throw new BadRequestException("Insufficient quantity of goods");
        }

        srcInventory.setQuantity(srcInventory.getQuantity() - transferDto.getQuantity());
        dstInventory.setQuantity(dstInventory.getQuantity() + transferDto.getQuantity());

        TransferToAnotherWarehouse warehouseToWarehouse = TransferToAnotherWarehouse.builder()
                .warehouseSrc(warehouseSrc)
                .warehouseDst(warehouseDst)
                .goods(goods)
                .quantity(transferDto.getQuantity())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        inventoryWarehouseRepository.save(dstInventory);
        inventoryWarehouseRepository.save(srcInventory);

        TransferToAnotherWarehouse savedTransfer = transferToAnotherWarehouseRepository.save(warehouseToWarehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransfer);
    }

    @Override
    public Page<TransferToAnotherWarehouse> findAllRecord(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return transferToAnotherWarehouseRepository.findAll(pageable);
    }
}
