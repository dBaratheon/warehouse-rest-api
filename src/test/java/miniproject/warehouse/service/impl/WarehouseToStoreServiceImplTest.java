package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.*;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.*;
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

class WarehouseToStoreServiceImplTest {

    @Mock
    private WarehouseToStoreRepository warehouseToStoreRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private InventoryStoreRepository inventoryStoreRepository;

    @Mock
    private InventoryWarehouseRepository inventoryWarehouseRepository;

    @InjectMocks
    private WarehouseToStoreServiceImpl warehouseToStoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferToStore() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Store store = new Store();
        store.setId("ST1");

        Goods goods = new Goods();
        goods.setId("G1");

        WarehouseToStore warehouseToStore = new WarehouseToStore();
        warehouseToStore.setQuantity(10L);

        InventoryStore inventoryStore = new InventoryStore();
        inventoryStore.setStore(store);
        inventoryStore.setGoods(goods);
        inventoryStore.setQuantity(5L);

        InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
        inventoryWarehouse.setWarehouse(warehouse);
        inventoryWarehouse.setGoods(goods);
        inventoryWarehouse.setQuantity(15L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));
        when(storeRepository.findById(store.getId())).thenReturn(Optional.of(store));
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.of(goods));
        when(inventoryStoreRepository.findFirstByStoreAndGoods(store, goods)).thenReturn(inventoryStore);
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouse, goods)).thenReturn(inventoryWarehouse);
        when(inventoryStoreRepository.save(inventoryStore)).thenReturn(inventoryStore);
        when(inventoryWarehouseRepository.save(inventoryWarehouse)).thenReturn(inventoryWarehouse);
        when(warehouseToStoreRepository.save(warehouseToStore)).thenReturn(warehouseToStore);

        ResponseEntity<WarehouseToStore> response = warehouseToStoreService.transferToStore(warehouse, store, goods, warehouseToStore);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(warehouseToStore, response.getBody());
        verify(warehouseToStoreRepository, times(1)).save(warehouseToStore);
        verify(inventoryStoreRepository, times(1)).save(inventoryStore);
        verify(inventoryWarehouseRepository, times(2)).save(inventoryWarehouse);
    }

    @Test
    void testTransferToStoreWarehouseNotFound() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Store store = new Store();
        store.setId("ST1");

        Goods goods = new Goods();
        goods.setId("G1");

        WarehouseToStore warehouseToStore = new WarehouseToStore();
        warehouseToStore.setQuantity(10L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> warehouseToStoreService.transferToStore(warehouse, store, goods, warehouseToStore));

        assertEquals("Warehouse not found", exception.getMessage());
        verify(warehouseToStoreRepository, times(0)).save(any(WarehouseToStore.class));
        verify(inventoryStoreRepository, times(0)).save(any(InventoryStore.class));
        verify(inventoryWarehouseRepository, times(0)).save(any(InventoryWarehouse.class));
    }

    @Test
    void testTransferToStoreInsufficientQuantity() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId("WH1");

        Store store = new Store();
        store.setId("ST1");

        Goods goods = new Goods();
        goods.setId("G1");

        WarehouseToStore warehouseToStore = new WarehouseToStore();
        warehouseToStore.setQuantity(10L);

        InventoryWarehouse inventoryWarehouse = new InventoryWarehouse();
        inventoryWarehouse.setQuantity(5L);

        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));
        when(storeRepository.findById(store.getId())).thenReturn(Optional.of(store));
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.of(goods));
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouse, goods)).thenReturn(inventoryWarehouse);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> warehouseToStoreService.transferToStore(warehouse, store, goods, warehouseToStore));

        assertEquals("Quantity isn't enough", exception.getMessage());
        verify(warehouseToStoreRepository, times(0)).save(any(WarehouseToStore.class));
        verify(inventoryStoreRepository, times(0)).save(any(InventoryStore.class));
        verify(inventoryWarehouseRepository, times(0)).save(any(InventoryWarehouse.class));
    }

    @Test
    void testFindAll() {
        List<WarehouseToStore> warehouseToStoreList = List.of(new WarehouseToStore(), new WarehouseToStore());

        when(warehouseToStoreRepository.findAll()).thenReturn(warehouseToStoreList);

        List<WarehouseToStore> result = warehouseToStoreService.findAll();

        assertEquals(2, result.size());
        assertSame(warehouseToStoreList, result);
        verify(warehouseToStoreRepository, times(1)).findAll();
    }
}
