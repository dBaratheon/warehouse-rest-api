package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.InventoryWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<InventoryWarehouse> findAllInventoryWarehouse() {
        List<InventoryWarehouse> inventoryWarehouseList = inventoryWarehouseRepository.findAll();
        if (inventoryWarehouseList.isEmpty()){
            throw new NotFoundException("Inventory is empty");
        }
        return inventoryWarehouseList;
    }
}
