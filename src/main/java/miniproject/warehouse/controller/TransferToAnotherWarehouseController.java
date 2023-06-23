package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.service.TransferToAnotherWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferToAnotherWarehouseController {
    @Autowired
    TransferToAnotherWarehouseService transferToAnotherWarehouseService;

    @PostMapping("/transfer/wtw/{warehouseSrc}/{warehouseDst}/{goodsId}")
    public ResponseEntity<TransferToAnotherWarehouse> transfer(@PathVariable Warehouse warehouseSrc,
                                                               @PathVariable Warehouse warehouseDst,
                                                               @PathVariable Goods goodsId,
                                                               @RequestBody TransferToAnotherWarehouse transfer){
        return transferToAnotherWarehouseService.transfer(warehouseSrc, warehouseDst, goodsId, transfer);
    }

    @GetMapping("/transfer/wtw")
    public List<TransferToAnotherWarehouse> findAll(){
        return transferToAnotherWarehouseService.findAll();
    }
}
