package miniproject.warehouse.controller;

import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.service.InventoryStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/store")
public class InventoryStoreController {
    @Autowired
    private InventoryStoreService inventoryStoreService;

    @GetMapping
    ResponseEntity<Page<InventoryStore>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                 @RequestParam(defaultValue = "5") int pageSize) {
        Page<InventoryStore> page = inventoryStoreService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{storeId}")
    List<InventoryStore> findByStoreId(@PathVariable String storeId){
        return inventoryStoreService.findByStoreId(storeId);
    }

    @GetMapping("/goods/{goodsId}")
    List<InventoryStore> findByGoodsId(@PathVariable String goodsId){
        return inventoryStoreService.findByGoodsId(goodsId);
    }
}
