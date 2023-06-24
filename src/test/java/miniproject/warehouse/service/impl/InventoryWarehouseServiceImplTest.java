package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.exception.NotFoundException;
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
    void testFindByWarehouseId() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        inventoryWarehouseList.add(new InventoryWarehouse());
        when(inventoryWarehouseRepository.findAllByWarehouseId("123")).thenReturn(inventoryWarehouseList);

        // Call the service method
        List<InventoryWarehouse> result = inventoryWarehouseService.findByWarehouseId("123");

        // Verify the result
        assertEquals(inventoryWarehouseList, result);
    }

    @Test
    void testFindByWarehouseIdNotFound() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        when(inventoryWarehouseRepository.findAllByWarehouseId("123")).thenReturn(inventoryWarehouseList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryWarehouseService.findByWarehouseId("123"));
    }

    @Test
    void testFindByGoodsId() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        inventoryWarehouseList.add(new InventoryWarehouse());
        when(inventoryWarehouseRepository.findAllByGoodsId("456")).thenReturn(inventoryWarehouseList);

        // Call the service method
        List<InventoryWarehouse> result = inventoryWarehouseService.findByGoodsId("456");

        // Verify the result
        assertEquals(inventoryWarehouseList, result);
    }

    @Test
    void testFindByGoodsIdNotFound() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        when(inventoryWarehouseRepository.findAllByGoodsId("456")).thenReturn(inventoryWarehouseList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryWarehouseService.findByGoodsId("456"));
    }

    @Test
    void testFindAllInventoryWarehouse() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        inventoryWarehouseList.add(new InventoryWarehouse());
        when(inventoryWarehouseRepository.findAll()).thenReturn(inventoryWarehouseList);

        // Call the service method
        List<InventoryWarehouse> result = inventoryWarehouseService.findAllInventoryWarehouse();

        // Verify the result
        assertEquals(inventoryWarehouseList, result);
    }

    @Test
    void testFindAllInventoryWarehouseEmpty() {
        // Mock repository response
        List<InventoryWarehouse> inventoryWarehouseList = new ArrayList<>();
        when(inventoryWarehouseRepository.findAll()).thenReturn(inventoryWarehouseList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryWarehouseService.findAllInventoryWarehouse());
    }
}
