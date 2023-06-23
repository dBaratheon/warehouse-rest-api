package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.SupplyToWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.service.SupplyToWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplyToWarehouseController {
    @Autowired
    SupplyToWarehouseService supplyToWarehouseService;

    @PostMapping("/transfer/stw/{warehouseId}/{goodsId}")
    public ResponseEntity<SupplyToWarehouse> supply(@PathVariable("warehouseId")Warehouse warehouseId,
                                                    @PathVariable("goodsId")Goods goodsId,
                                                    @RequestBody SupplyToWarehouse supplyToWarehouse){
        return supplyToWarehouseService.supplyToWarehouse(warehouseId, goodsId, supplyToWarehouse);
    }

    @GetMapping("/transfer/stw")
    public List<SupplyToWarehouse> findAll(){
        return supplyToWarehouseService.findAll();
    }
}
