package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Store;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.StoreRepository;
import miniproject.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        if (!StringUtils.hasText(store.getName())){
            throw new BadRequestException("name must be filled");
        }
        if (!StringUtils.hasText(store.getLocation())){
            throw new BadRequestException("location must be filled");
        }
        store.setId("ST"+storeRepository.count());
        store.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return storeRepository.save(store);
    }

    @Override
    public Store findByStoreId(String storeId) {
        return storeRepository.findById(storeId).orElseThrow(() -> new NotFoundException("Store with id "+storeId+" not found!"));
    }

    @Override
    public List<Store> findAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public Store updateStore(String storeId, Store store) {
        Store original = findByStoreId(storeId);
        if (store.getName() != null){
            original.setName(store.getName());
        }
        if (store.getLocation() != null){
            original.setLocation(store.getLocation());
        }
        original.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return storeRepository.save(original);
    }

    @Override
    public String deleteStore(String storeId) {
        if (storeRepository.existsById(storeId)){
            storeRepository.deleteById(storeId);
            return "Store with id "+storeId+" deleted";
        }
        else {
            return "Store with id " + storeId + " not found!";
        }
    }
}
