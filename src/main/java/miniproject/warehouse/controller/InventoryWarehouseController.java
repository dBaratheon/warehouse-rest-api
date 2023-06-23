package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.service.InventoryWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryWarehouseController {
    @Autowired
    private InventoryWarehouseService inventoryWarehouseService;

    @GetMapping("/warehouse/inventory")
    public List<InventoryWarehouse> findAllInventoryWarehouse(){
        return inventoryWarehouseService.findAllInventoryWarehouse();
    }

    @GetMapping("/warehouse/inventory/{warehouseId}")
    public List<InventoryWarehouse> findByWarehouseId(@PathVariable String warehouseId){
        return inventoryWarehouseService.findByWarehouseId(warehouseId);
    }
}