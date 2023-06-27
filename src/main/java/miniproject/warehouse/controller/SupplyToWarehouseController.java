package miniproject.warehouse.controller;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.SupplyToWarehouse;
import miniproject.warehouse.service.SupplyToWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer/stw")
public class SupplyToWarehouseController {
    @Autowired
    SupplyToWarehouseService supplyToWarehouseService;

    @PostMapping
    public ResponseEntity<SupplyToWarehouse> supply(@RequestBody TransferDto transferDto) {
        return supplyToWarehouseService.supplyToWarehouse(transferDto);
    }

    @GetMapping
    public ResponseEntity<Page<SupplyToWarehouse>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "5") int pageSize) {
        Page<SupplyToWarehouse> page = supplyToWarehouseService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }
}
