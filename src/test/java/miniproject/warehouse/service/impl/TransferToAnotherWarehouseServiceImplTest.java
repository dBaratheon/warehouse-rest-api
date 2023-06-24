package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.InventoryWarehouse;
import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import miniproject.warehouse.entity.Warehouse;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import miniproject.warehouse.repository.InventoryWarehouseRepository;
import miniproject.warehouse.repository.TransferToAnotherWarehouseRepository;
import miniproject.warehouse.repository.WarehouseRepository;
import miniproject.warehouse.service.TransferToAnotherWarehouseService;
import miniproject.warehouse.service.impl.TransferToAnotherWarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.system.SystemProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferToAnotherWarehouseServiceImplTest {
    @Mock
    TransferToAnotherWarehouseRepository transferToAnotherWarehouseRepository;
    @Mock
    WarehouseRepository warehouseRepository;
    @Mock
    GoodsRepository goodsRepository;
    @Mock
    InventoryWarehouseRepository inventoryWarehouseRepository;

    @InjectMocks
    TransferToAnotherWarehouseService transferToAnotherWarehouseService = new TransferToAnotherWarehouseServiceImpl();

    private Warehouse warehouseSrc;
    private Warehouse warehouseDst;
    private Goods goods;
    private TransferToAnotherWarehouse transfer;

    @BeforeEach
    void setUp() {
        warehouseSrc = new Warehouse();
        warehouseSrc.setId("1L");

        warehouseDst = new Warehouse();
        warehouseDst.setId("2L");

        goods = new Goods();
        goods.setId("1L");

        transfer = new TransferToAnotherWarehouse();
        transfer.setQuantity(10L);
    }

    @Test
    void transfer_WhenWarehouseNotFound_ShouldThrowNotFoundException() {
        when(warehouseRepository.findById("1L")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            transferToAnotherWarehouseService.transfer(warehouseSrc, warehouseDst, goods, transfer);
        });

        verify(transferToAnotherWarehouseRepository, never()).save(any(TransferToAnotherWarehouse.class));
    }

    @Test
    void transfer_WhenGoodsQuantityNotEnough_ShouldThrowBadRequestException() {
        InventoryWarehouse inventoryWarehouseSrc = new InventoryWarehouse();
        inventoryWarehouseSrc.setQuantity(5L);

        when(warehouseRepository.findById("1L")).thenReturn(Optional.of(warehouseSrc));
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goods)).thenReturn(inventoryWarehouseSrc);

        assertThrows(BadRequestException.class, () -> {
            transferToAnotherWarehouseService.transfer(warehouseSrc, warehouseDst, goods, transfer);
        });

        verify(transferToAnotherWarehouseRepository, never()).save(any(TransferToAnotherWarehouse.class));
    }

    @Test
    void transfer_WhenTransferSuccessful_ShouldReturnTransferToAnotherWarehouse() {
        InventoryWarehouse inventoryWarehouseSrc = new InventoryWarehouse();
        inventoryWarehouseSrc.setQuantity(20L);

        InventoryWarehouse inventoryWarehouseDst = new InventoryWarehouse();
        inventoryWarehouseDst.setQuantity(5L);

        when(warehouseRepository.findById("1L")).thenReturn(Optional.of(warehouseSrc));
        when(warehouseRepository.findById("2L")).thenReturn(Optional.of(warehouseDst));
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseSrc, goods)).thenReturn(inventoryWarehouseSrc);
        when(inventoryWarehouseRepository.findByWarehouseAndGoods(warehouseDst, goods)).thenReturn(inventoryWarehouseDst);
        when(transferToAnotherWarehouseRepository.count()).thenReturn(1L);
        when(transferToAnotherWarehouseRepository.save(any(TransferToAnotherWarehouse.class))).thenReturn(new TransferToAnotherWarehouse());

        ResponseEntity<TransferToAnotherWarehouse> response = transferToAnotherWarehouseService.transfer(warehouseSrc, warehouseDst, goods, transfer);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        verify(inventoryWarehouseRepository).save(inventoryWarehouseDst);
        verify(transferToAnotherWarehouseRepository).save(any(TransferToAnotherWarehouse.class));
    }

    @Test
    void findAll_ShouldReturnListOfTransferToAnotherWarehouse() {
        when(transferToAnotherWarehouseRepository.findAll()).thenReturn(List.of(new TransferToAnotherWarehouse()));

        List<TransferToAnotherWarehouse> transfers = transferToAnotherWarehouseService.findAll();

        assertNotNull(transfers);
        assertFalse(transfers.isEmpty());
        assertEquals(1, transfers.size());

        verify(transferToAnotherWarehouseRepository).findAll();
    }
}
