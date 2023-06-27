package miniproject.warehouse.controller;

import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.service.InventoryWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/warehouse")
public class InventoryWarehouseController {
    @Autowired
    private InventoryWarehouseService inventoryWarehouseService;

    @GetMapping
    public ResponseEntity<Page<InventoryWarehouse>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                            @RequestParam(defaultValue = "5") int pageSize) {
        Page<InventoryWarehouse> page = inventoryWarehouseService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{warehouseId}")
    public List<InventoryWarehouse> findByWarehouseId(@PathVariable String warehouseId){
        return inventoryWarehouseService.findByWarehouseId(warehouseId);
    }

    @GetMapping("/goods/{goodsId}")
    public List<InventoryWarehouse> findByGoodsId(@PathVariable String goodsId){
        return inventoryWarehouseService.findByGoodsId(goodsId);
    }
}
