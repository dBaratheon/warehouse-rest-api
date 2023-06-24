package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.SupplyToWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.repository.SupplyToWarehouseRepository;
import miniproject.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyToWarehouseServiceImplTest {

    @Mock
    private SupplyToWarehouseRepository supplyToWarehouseRepository;

    @Mock
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private SupplyToWarehouseServiceImpl supplyToWarehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSupplyToWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Goods goods = new Goods();
        goods.setId("G1");

        SupplyToWarehouse supplyToWarehouse = new SupplyToWarehouse();
        supplyToWarehouse.setQuantity(10L);

        InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
        inventoryWarehouse.setQuantity(5L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.of(goods));
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouse, goods)).thenReturn(inventoryWarehouse);
        when(inventoryWarehouseRepository.save(inventoryWarehouse)).thenReturn(inventoryWarehouse);
        when(supplyToWarehouseRepository.save(supplyToWarehouse)).thenReturn(supplyToWarehouse);

        ResponseEntity<SupplyToWarehouse> response = supplyToWarehouseService.supplyToWarehouse(warehouse, goods, supplyToWarehouse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(supplyToWarehouse, response.getBody());
        verify(supplyToWarehouseRepository, times(1)).save(supplyToWarehouse);
        verify(inventoryWarehouseRepository, times(1)).save(inventoryWarehouse);
    }

    @Test
    void testSupplyToWarehouseWarehouseNotFound() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Goods goods = new Goods();
        goods.setId("G1");

        SupplyToWarehouse supplyToWarehouse = new SupplyToWarehouse();
        supplyToWarehouse.setQuantity(10L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> supplyToWarehouseService.supplyToWarehouse(warehouse, goods, supplyToWarehouse));

        assertEquals("Warehouse not found", exception.getMessage());
        verify(supplyToWarehouseRepository, times(0)).save(any(SupplyToWarehouse.class));
        verify(inventoryWarehouseRepository, times(0)).save(any(InventoryWarehouse.class));
    }

    @Test
    void testSupplyToWarehouseGoodsNotFound() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Goods goods = new Goods();
        goods.setId("G1");

        SupplyToWarehouse supplyToWarehouse = new SupplyToWarehouse();
        supplyToWarehouse.setQuantity(10L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> supplyToWarehouseService.supplyToWarehouse(warehouse, goods, supplyToWarehouse));

        assertEquals("Goods not found", exception.getMessage());
        verify(supplyToWarehouseRepository, times(0)).save(any(SupplyToWarehouse.class));
        verify(inventoryWarehouseRepository, times(0)).save(any(InventoryWarehouse.class));
    }

    @Test
    void testSupplyToWarehouseWithNewInventoryWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Goods goods = new Goods();
        goods.setId("G1");

        SupplyToWarehouse supplyToWarehouse = new SupplyToWarehouse();
        supplyToWarehouse.setQuantity(10L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.of(goods));
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouse, goods)).thenReturn(null);
        when(inventoryWarehouseRepository.count()).thenReturn(1L);
        when(inventoryWarehouseRepository.save(any(InventoryWarehouse.class))).thenReturn(null);
        when(supplyToWarehouseRepository.save(supplyToWarehouse)).thenReturn(supplyToWarehouse);

        ResponseEntity<SupplyToWarehouse> response = supplyToWarehouseService.supplyToWarehouse(warehouse, goods, supplyToWarehouse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(supplyToWarehouse, response.getBody());
        verify(supplyToWarehouseRepository, times(1)).save(supplyToWarehouse);
        verify(inventoryWarehouseRepository, times(1)).save(any(InventoryWarehouse.class));
    }

    @Test
    void testFindAll() {
        List<SupplyToWarehouse> supplyToWarehouseList = List.of(new SupplyToWarehouse(), new SupplyToWarehouse());

        when(supplyToWarehouseRepository.findAll()).thenReturn(supplyToWarehouseList);

        List<SupplyToWarehouse> result = supplyToWarehouseService.findAll();

        assertEquals(2, result.size());
        assertSame(supplyToWarehouseList, result);
        verify(supplyToWarehouseRepository, times(1)).findAll();
    }
}
