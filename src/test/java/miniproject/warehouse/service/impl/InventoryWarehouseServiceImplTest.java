package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryWarehouseServiceImplTest {

    @Mock
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @InjectMocks
    private InventoryWarehouseServiceImpl inventoryWarehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByWarehouseIdExistingWarehouseIdReturnsInventoryWarehouses() {
        String warehouseId = "existingId";
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        inventoryWarehouseList.add(new InventoryWarehouse());
        inventoryWarehouseList.add(new InventoryWarehouse());

        when(inventoryWarehouseRepository.findAllByWarehouseId(warehouseId)).thenReturn(inventoryWarehouseList);

        List<InventoryWarehouse> foundInventoryWarehouseList = inventoryWarehouseService.findByWarehouseId(warehouseId);

        assertEquals(inventoryWarehouseList.size(), foundInventoryWarehouseList.size());
    }
}