package miniproject.warehouse.service.impl;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.*;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.*;
import miniproject.warehouse.service.WarehouseToStoreService;
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
    @Transactional
    public ResponseEntity<WarehouseToStore> transferToStore(TransferDto transferDto) {
        Warehouse warehouseSrc = warehouseRepository.findById(transferDto.getWarehouseSrc())
                .orElseThrow(() -> new NotFoundException("Source warehouse not found"));

        Store storeDst = storeRepository.findById(transferDto.getStoreDst())
                .orElseThrow(() -> new NotFoundException("Destination store not found"));

        Goods goods = goodsRepository.findById(transferDto.getGoods())
                .orElseThrow(() -> new NotFoundException("Goods not found"));

        InventoryWarehouse srcInventory = inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goods);
        InventoryStore dstInventory = inventoryStoreRepository.findFirstByStoreAndGoods(storeDst, goods);

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

        WarehouseToStore warehouseToWarehouse = WarehouseToStore.builder()
                .warehouseSrc(warehouseSrc)
                .storeDst(storeDst)
                .goods(goods)
                .quantity(transferDto.getQuantity())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        inventoryStoreRepository.save(dstInventory);
        inventoryWarehouseRepository.save(srcInventory);

        WarehouseToStore savedTransfer = warehouseToStoreRepository.save(warehouseToWarehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransfer);
    }

    @Override
    public Page<WarehouseToStore> findAllRecord(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return warehouseToStoreRepository.findAll(pageable);
    }
}
