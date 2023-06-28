package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryStore;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryStoreRepository;
import miniproject.warehouse.service.InventoryStoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryStoreServiceImplTest {

    @Mock
    private InventoryStoreRepository inventoryStoreRepository;

    @InjectMocks
    private InventoryStoreServiceImpl inventoryStoreService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRecord() {
        List<InventoryStore> inventoryStores = new ArrayList<>();
        inventoryStores.add(new InventoryStore());
        inventoryStores.add(new InventoryStore());
        Page<InventoryStore> inventoryStorePage = new PageImpl<>(inventoryStores);

        Mockito.when(inventoryStoreRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(inventoryStorePage);

        Page<InventoryStore> result = inventoryStoreService.findAllRecord(0, 10);

        Assertions.assertEquals(inventoryStorePage, result);
        Mockito.verify(inventoryStoreRepository).findAll(Mockito.any(PageRequest.class));
    }
}