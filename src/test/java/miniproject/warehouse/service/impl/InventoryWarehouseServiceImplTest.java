package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
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

public class InventoryWarehouseServiceImplTest {

    @Mock
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @InjectMocks
    private InventoryWarehouseServiceImpl inventoryWarehouseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRecord() {
        List<InventoryWarehouse> inventoryWarehouses = new ArrayList<>();
        inventoryWarehouses.add(new InventoryWarehouse());
        inventoryWarehouses.add(new InventoryWarehouse());
        Page<InventoryWarehouse> inventoryWarehousePage = new PageImpl<>(inventoryWarehouses);

        Mockito.when(inventoryWarehouseRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(inventoryWarehousePage);

        Page<InventoryWarehouse> result = inventoryWarehouseService.findAllRecord(0, 10);

        Assertions.assertEquals(inventoryWarehousePage, result);
        Mockito.verify(inventoryWarehouseRepository).findAll(Mockito.any(PageRequest.class));
    }
}