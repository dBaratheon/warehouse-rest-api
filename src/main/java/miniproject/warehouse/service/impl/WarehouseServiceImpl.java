package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        if (!StringUtils.hasText(warehouse.getName())){
            throw new BadRequestException("name must be filled");
        }
        if (!StringUtils.hasText(warehouse.getLocation())) {
            throw new BadRequestException("location must be filled");
        }
        warehouse.setId(UUID.randomUUID().toString());
        warehouse.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse findByWarehouseId(String warehouseId) {
        return warehouseRepository.findById(warehouseId).orElseThrow(()-> new NotFoundException("Warehouse id not found"));
    }

    @Override
    public List<Warehouse> findAllWarehouse() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        if (warehouses.isEmpty()){
            throw new NotFoundException("Warehouse is empty");
        }
        return warehouses;
    }

    @Override
    public Warehouse updateWarehouse(String warehouseId, Warehouse warehouse) {
        Warehouse original = findByWarehouseId(warehouseId);
        if (warehouse.getName() != null){
            original.setName(warehouse.getName());
        }
        if (warehouse.getLocation() != null) {
            original.setLocation(warehouse.getLocation());
        }
        original.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return warehouseRepository.save(original);
    }

    @Override
    public String deleteWarehouse(String warehouseId) {
        if (warehouseRepository.existsById(warehouseId)){
        warehouseRepository.deleteById(warehouseId);
        return "Warehouse with id "+warehouseId+" deleted";
        }
        else {
            return "Warehouse with id " + warehouseId + " not found";
        }
    }
}
