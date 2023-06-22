package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Store;
import miniproject.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/store")
    public Store createStore(@RequestBody Store store){
        return storeService.createStore(store);
    }

    @GetMapping("/store/{storeId}")
    public Store findByStoreId(@PathVariable String storeId){
        return storeService.findByStoreId(storeId);
    }

    @GetMapping("/store")
    public List<Store> findAllStore(){
        return storeService.findAllStore();
    }

    @PostMapping("/store/{storeId}")
    public Store updateStore(@PathVariable String storeId, @RequestBody Store store){
        return storeService.updateStore(storeId, store);
    }

    @DeleteMapping("/store/{storeId}")
    String deleteStore(@PathVariable String storeId){
        return storeService.deleteStore(storeId);
    }
}
