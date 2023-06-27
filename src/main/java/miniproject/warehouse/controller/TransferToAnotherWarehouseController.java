package miniproject.warehouse.controller;

import miniproject.warehouse.dto.TransferDto;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.service.TransferToAnotherWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer/wtw")
public class TransferToAnotherWarehouseController {
    @Autowired
    TransferToAnotherWarehouseService transferToAnotherWarehouseService;

    @PostMapping
    public ResponseEntity<TransferToAnotherWarehouse> transfer(@RequestBody TransferDto transferDto) {
        return transferToAnotherWarehouseService.transfer(transferDto);
    }

    @GetMapping
    public ResponseEntity<Page<TransferToAnotherWarehouse>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                                    @RequestParam(defaultValue = "5") int pageSize) {
        Page<TransferToAnotherWarehouse> page = transferToAnotherWarehouseService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }
}
