package miniproject.warehouse.controller;

import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.service.InventoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryStoreController {
    @Autowired
    private InventoryStoreService inventoryStoreService;

    @GetMapping("/store/inventory/{storeId}")
    public List<InventoryStore> findByStoreId(@PathVariable String storeId){
        return inventoryStoreService.findByStoreId(storeId);
    }

    @GetMapping("/store/inventory")
    public List<InventoryStore> findAll(){
        return inventoryStoreService.findAllInventoryStore();
    }
}
