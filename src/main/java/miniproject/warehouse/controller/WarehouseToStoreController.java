package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.Store;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.entity.WarehouseToStore;
import miniproject.warehouse.service.WarehouseToStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseToStoreController {
    @Autowired
    private WarehouseToStoreService warehouseToStoreService;

    @PostMapping("/transfer/wts/{warehouseSrc}/{storeDst}/{goodsId}")
    public ResponseEntity<WarehouseToStore> transferToStore(@PathVariable("warehouseSrc")Warehouse warehouseSrc,
                                                     @PathVariable("storeDst")Store storeDst,
                                                     @PathVariable("goodsId")Goods goodsId,
                                                     @RequestBody WarehouseToStore warehouseToStore) {
        return warehouseToStoreService.transferToStore(warehouseSrc, storeDst, goodsId, warehouseToStore);
    }

    @GetMapping("/transfer/wts")
    public List<WarehouseToStore> findALl(){
        return warehouseToStoreService.findAll();
    }
}
