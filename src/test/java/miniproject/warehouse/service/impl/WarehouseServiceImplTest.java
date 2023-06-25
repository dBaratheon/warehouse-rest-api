package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWarehouse_ValidInput_ReturnsCreatedWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test Warehouse");
        warehouse.setLocation("Test Location");

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);

        assertNotNull(createdWarehouse);
        assertNotNull(createdWarehouse.getId());
        assertEquals(warehouse.getName(), createdWarehouse.getName());
        assertEquals(warehouse.getLocation(), createdWarehouse.getLocation());
        assertNotNull(createdWarehouse.getCreatedAt());
        verify(warehouseRepository, times(1)).save(warehouse);
    }
}