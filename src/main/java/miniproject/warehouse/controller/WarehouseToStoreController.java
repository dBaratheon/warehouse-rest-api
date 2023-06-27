package miniproject.warehouse.controller;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.WarehouseToStore;
import miniproject.warehouse.service.WarehouseToStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer/wts")
public class WarehouseToStoreController {
    @Autowired
    private WarehouseToStoreService warehouseToStoreService;

    @PostMapping
    public ResponseEntity<WarehouseToStore> transferToStore(@RequestBody TransferDto transferDto) {
        return warehouseToStoreService.transferToStore(transferDto);
    }

    @GetMapping
    public ResponseEntity<Page<WarehouseToStore>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                          @RequestParam(defaultValue = "5") int pageSize) {
        Page<WarehouseToStore> page = warehouseToStoreService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }
}
