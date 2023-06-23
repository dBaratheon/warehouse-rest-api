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
        return inventoryWarehouseRepository.findAllByWarehouseId(warehouseId);
    }

    @Override
    public List<InventoryWarehouse> findByGoodsId(String goodsId) {
        return inventoryWarehouseRepository.findAllByGoodsId(goodsId);
    }

    @Override
    public List<InventoryWarehouse> findAllInventoryWarehouse() {
        return inventoryWarehouseRepository.findAll();
    }
}
