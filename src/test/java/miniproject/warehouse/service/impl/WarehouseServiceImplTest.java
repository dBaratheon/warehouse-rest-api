package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void testCreateWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setLocation("Location 1");
        when(warehouseRepository.count()).thenReturn(1L);
        when(warehouseRepository.save(warehouse)).thenReturn(warehouse);

        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);

        assertEquals("WH1", createdWarehouse.getId());
        assertNotNull(createdWarehouse.getCreatedAt());
        verify(warehouseRepository, times(1)).save(warehouse);
    }

    @Test
    void testCreateWarehouseWithEmptyName() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("");
        warehouse.setLocation("Location 1");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> warehouseService.createWarehouse(warehouse));

        assertEquals("name must be filled", exception.getMessage());
        verifyNoInteractions(warehouseRepository);
    }

    @Test
    void testCreateWarehouseWithEmptyLocation() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setLocation("");

        BadRequestException exception = assertThrows(BadRequestException.class, () -> warehouseService.createWarehouse(warehouse));

        assertEquals("location must be filled", exception.getMessage());
        verifyNoInteractions(warehouseRepository);
    }

    @Test
    void testFindByWarehouseId() {
        String warehouseId = "WH1";
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));

        Warehouse foundWarehouse = warehouseService.findByWarehouseId(warehouseId);

        assertSame(warehouse, foundWarehouse);
        verify(warehouseRepository, times(1)).findById(warehouseId);
    }

    @Test
    void testFindByWarehouseIdNotFound() {
        String warehouseId = "WH1";
        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> warehouseService.findByWarehouseId(warehouseId));

        assertEquals("Warehouse with id WH1 not found", exception.getMessage());
        verify(warehouseRepository, times(1)).findById(warehouseId);
    }

    @Test
    void testFindAllWarehouse() {
        List<Warehouse> warehouses = new ArrayList<>();
        when(warehouseRepository.findAll()).thenReturn(warehouses);

        List<Warehouse> foundWarehouses = warehouseService.findAllWarehouse();

        assertSame(warehouses, foundWarehouses);
        verify(warehouseRepository, times(1)).findAll();
    }

    @Test
    void testUpdateWarehouse() {
        String warehouseId = "WH1";
        Warehouse originalWarehouse = new Warehouse();
        originalWarehouse.setId(warehouseId);
        originalWarehouse.setName("Warehouse 1");
        originalWarehouse.setLocation("Location 1");
        originalWarehouse.setCreatedAt(null);

        Warehouse updatedWarehouse = new Warehouse();
        updatedWarehouse.setName("Updated Warehouse 1");
        updatedWarehouse.setLocation("Updated Location 1");

        when(warehouseRepository.existsById(warehouseId)).thenReturn(true);
        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(originalWarehouse));
        when(warehouseRepository.save(originalWarehouse)).thenReturn(originalWarehouse);

        Warehouse result = warehouseService.updateWarehouse(warehouseId, updatedWarehouse);

        assertSame(originalWarehouse, result);
        assertEquals("Updated Warehouse 1", result.getName());
        assertEquals("Updated Location 1", result.getLocation());
        assertNotNull(result.getCreatedAt());
        verify(warehouseRepository, times(1)).save(originalWarehouse);
    }

    @Test
    void testUpdateWarehouseNotFound() {
        String warehouseId = "WH1";
        Warehouse updatedWarehouse = new Warehouse();

        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> warehouseService.updateWarehouse(warehouseId, updatedWarehouse));

        assertEquals("Warehouse with id WH1 not found", exception.getMessage());
        verify(warehouseRepository, times(0)).save(any(Warehouse.class));
    }

    @Test
    void testDeleteWarehouse() {
        String warehouseId = "WH1";
        when(warehouseRepository.existsById(warehouseId)).thenReturn(true);

        String result = warehouseService.deleteWarehouse(warehouseId);

        assertEquals("Warehouse with id WH1 deleted", result);
        verify(warehouseRepository, times(1)).deleteById(warehouseId);
    }

    @Test
    void testDeleteWarehouseNotFound() {
        String warehouseId = "WH1";
        when(warehouseRepository.existsById(warehouseId)).thenReturn(false);

        String result = warehouseService.deleteWarehouse(warehouseId);

        assertEquals("Warehouse with id WH1 not found", result);
        verify(warehouseRepository, times(0)).deleteById(warehouseId);
    }
}
