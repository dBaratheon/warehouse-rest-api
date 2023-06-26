package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryStore;
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
    void findAllReturnsAllInventoryStores() {
        List<InventoryStore> inventoryStoreList = new ArrayList<>();
        inventoryStoreList.add(new InventoryStore());
        inventoryStoreList.add(new InventoryStore());

        when(inventoryStoreRepository.findAll()).thenReturn(inventoryStoreList);

        List<InventoryStore> foundInventoryStoreList = inventoryStoreService.findAll();

        assertEquals(inventoryStoreList.size(), foundInventoryStoreList.size());
        verify(inventoryStoreRepository, times(1)).findAll();
    }
}