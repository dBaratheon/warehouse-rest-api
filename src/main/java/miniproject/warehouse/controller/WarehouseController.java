package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.createWarehouse(warehouse);
    }

    @GetMapping("/{warehouseId}")
    public Warehouse findByWarehouseId(@PathVariable String warehouseId){
        return warehouseService.findByWarehouseId(warehouseId);
    }

    @GetMapping
    public ResponseEntity<Page<Warehouse>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "5") int pageSize) {
        Page<Warehouse> page = warehouseService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{warehouseId}")
    public Warehouse updateWarehouse(@PathVariable String warehouseId, @RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(warehouseId, warehouse);
    }

    @DeleteMapping("/{warehouseId}")
    String deleteWarehouse(@PathVariable String warehouseId){
        return warehouseService.deleteWarehouse(warehouseId);
    }
}
