package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/warehouse")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.createWarehouse(warehouse);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public Warehouse findByWarehouseId(@PathVariable String warehouseId){
        return warehouseService.findByWarehouseId(warehouseId);
    }

    @GetMapping("/warehouse")
    public List<Warehouse> findAllWarehouse(){
        return warehouseService.findAllWarehouse();
    }

    @PostMapping("/warehouse/{warehouseId}")
    public Warehouse updateWarehouse(@PathVariable String warehouseId, @RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(warehouseId, warehouse);
    }

    @DeleteMapping("/warehouse/{warehouseId}")
    String deleteWarehouse(@PathVariable String warehouseId){
        return warehouseService.deleteWarehouse(warehouseId);
    }
}
