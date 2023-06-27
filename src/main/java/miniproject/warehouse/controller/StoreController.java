package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Store;
import miniproject.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping
    public Store createStore(@RequestBody Store store){
        return storeService.createStore(store);
    }

    @GetMapping("/{storeId}")
    public Store findByStoreId(@PathVariable String storeId){
        return storeService.findByStoreId(storeId);
    }

    @GetMapping
    public ResponseEntity<Page<Store>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "5") int pageSize) {
        Page<Store> page = storeService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{storeId}")
    public Store updateStore(@PathVariable String storeId, @RequestBody Store store){
        return storeService.updateStore(storeId, store);
    }

    @DeleteMapping("/{storeId}")
    String deleteStore(@PathVariable String storeId){
        return storeService.deleteStore(storeId);
    }
}
