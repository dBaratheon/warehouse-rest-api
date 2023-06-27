package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.service.InventoryWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryWarehouseServiceImpl implements InventoryWarehouseService {
    @Autowired
    InventoryWarehouseRepository inventoryWarehouseRepository;

    @Override
    public List<InventoryWarehouse> findByWarehouseId(String warehouseId) {
        List<InventoryWarehouse> inventoryWarehouseList = inventoryWarehouseRepository.findAllByWarehouseId(warehouseId);
        if (inventoryWarehouseList.isEmpty()){
            throw new NotFoundException("Warehouse id not found");
        }
        return inventoryWarehouseList;
    }

    @Override
    public List<InventoryWarehouse> findByGoodsId(String goodsId) {
        List<InventoryWarehouse> inventoryWarehouseList = inventoryWarehouseRepository.findAllByGoodsId(goodsId);
        if (inventoryWarehouseList.isEmpty()){
            throw new NotFoundException("Goods id not found");
        }
        return inventoryWarehouseList;
    }

    @Override
    public Page<InventoryWarehouse> findAllRecord(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return inventoryWarehouseRepository.findAll(pageable);
    }
}
