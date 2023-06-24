package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryStoreRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryStoreServiceImplTest {

    @Mock
    private InventoryStoreRepository inventoryStoreRepository;

    @InjectMocks
    private InventoryStoreServiceImpl inventoryStoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        inventoryStoreList.add(new InventoryStore());
        when(inventoryStoreRepository.findAll()).thenReturn(inventoryStoreList);

        // Call the service method
        List<InventoryStore> result = inventoryStoreService.findAll();

        // Verify the result
        assertEquals(inventoryStoreList, result);
    }

    @Test
    void testFindAllEmpty() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        when(inventoryStoreRepository.findAll()).thenReturn(inventoryStoreList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryStoreService.findAll());
    }

    @Test
    void testFindByStoreId() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        inventoryStoreList.add(new InventoryStore());
        when(inventoryStoreRepository.findAllByStoreId("123")).thenReturn(inventoryStoreList);

        // Call the service method
        List<InventoryStore> result = inventoryStoreService.findByStoreId("123");

        // Verify the result
        assertEquals(inventoryStoreList, result);
    }

    @Test
    void testFindByStoreIdNotFound() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        when(inventoryStoreRepository.findAllByStoreId("123")).thenReturn(inventoryStoreList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryStoreService.findByStoreId("123"));
    }

    @Test
    void testFindByGoodsId() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        inventoryStoreList.add(new InventoryStore());
        when(inventoryStoreRepository.findAllByGoodsId("456")).thenReturn(inventoryStoreList);

        // Call the service method
        List<InventoryStore> result = inventoryStoreService.findByGoodsId("456");

        // Verify the result
        assertEquals(inventoryStoreList, result);
    }

    @Test
    void testFindByGoodsIdNotFound() {
        // Mock repository response
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        when(inventoryStoreRepository.findAllByGoodsId("456")).thenReturn(inventoryStoreList);

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> inventoryStoreService.findByGoodsId("456"));
    }
}
