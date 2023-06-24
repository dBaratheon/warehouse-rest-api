package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Store;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.StoreRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStore() {
        // Prepare store object
        Store store = new Store();
        store.setName("Test Store");
        store.setLocation("Test Location");

        // Mock repository response
        when(storeRepository.count()).thenReturn(1L);
        when(storeRepository.save(store)).thenReturn(store);

        // Call the service method
        Store result = storeService.createStore(store);

        // Verify the result
        assertNotNull(result);
        assertEquals("ST1", result.getId());
        assertNotNull(result.getCreatedAt());
        assertEquals("Test Store", result.getName());
        assertEquals("Test Location", result.getLocation());

        // Verify repository method invocations
        verify(storeRepository, times(1)).count();
        verify(storeRepository, times(1)).save(store);
    }

    @Test
    void testCreateStoreMissingName() {
        // Prepare store object without name
        Store store = new Store();
        store.setLocation("Test Location");

        // Call the service method and verify BadRequestException
        assertThrows(BadRequestException.class, () -> storeService.createStore(store));
    }

    @Test
    void testCreateStoreMissingLocation() {
        // Prepare store object without location
        Store store = new Store();
        store.setName("Test Store");

        // Call the service method and verify BadRequestException
        assertThrows(BadRequestException.class, () -> storeService.createStore(store));
    }

    @Test
    void testFindByStoreId() {
        // Prepare store object
        Store store = new Store();
        store.setId("ST1");
        store.setName("Test Store");
        store.setLocation("Test Location");

        // Mock repository response
        when(storeRepository.findById("ST1")).thenReturn(Optional.of(store));

        // Call the service method
        Store result = storeService.findByStoreId("ST1");

        // Verify the result
        assertNotNull(result);
        assertEquals("ST1", result.getId());
        assertEquals("Test Store", result.getName());
        assertEquals("Test Location", result.getLocation());

        // Verify repository method invocations
        verify(storeRepository, times(1)).findById("ST1");
    }

    @Test
    void testFindByStoreIdNotFound() {
        // Mock repository response
        when(storeRepository.findById("ST1")).thenReturn(Optional.empty());

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> storeService.findByStoreId("ST1"));
    }

    @Test
    void testFindAllStore() {
        // Prepare store objects
        Store store1 = new Store();
        store1.setId("ST1");
        store1.setName("Store 1");
        store1.setLocation("Location 1");

        Store store2 = new Store();
        store2.setId("ST2");
        store2.setName("Store 2");
        store2.setLocation("Location 2");

        List<Store> storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);

        // Mock repository response
        when(storeRepository.findAll()).thenReturn(storeList);

        // Call the service method
        List<Store> result = storeService.findAllStore();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(store1, result.get(0));
        assertEquals(store2, result.get(1));

        // Verify repository method invocations
        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateStore() {
        // Prepare original store object
        Store originalStore = new Store();
        originalStore.setId("ST1");
        originalStore.setName("Original Store");
        originalStore.setLocation("Original Location");

        // Prepare updated store object
        Store updatedStore = new Store();
        updatedStore.setName("Updated Store");
        updatedStore.setLocation("Updated Location");

        // Mock repository response
        when(storeRepository.findById("ST1")).thenReturn(Optional.of(originalStore));
        when(storeRepository.save(originalStore)).thenReturn(originalStore);

        // Call the service method
        Store result = storeService.updateStore("ST1", updatedStore);

        // Verify the result
        assertNotNull(result);
        assertEquals("ST1", result.getId());
        assertNotNull(result.getCreatedAt());
        assertEquals("Updated Store", result.getName());
        assertEquals("Updated Location", result.getLocation());

        // Verify repository method invocations
        verify(storeRepository, times(1)).findById("ST1");
        verify(storeRepository, times(1)).save(originalStore);
    }

    @Test
    void testUpdateStoreNotFound() {
        // Prepare updated store object
        Store updatedStore = new Store();
        updatedStore.setName("Updated Store");
        updatedStore.setLocation("Updated Location");

        // Mock repository response
        when(storeRepository.findById("ST1")).thenReturn(Optional.empty());

        // Call the service method and verify NotFoundException
        assertThrows(NotFoundException.class, () -> storeService.updateStore("ST1", updatedStore));
    }

    @Test
    void testDeleteStoreExists() {
        // Mock repository response
        when(storeRepository.existsById("ST1")).thenReturn(true);

        // Call the service method
        String result = storeService.deleteStore("ST1");

        // Verify the result
        assertNotNull(result);
        assertEquals("Store with id ST1 deleted", result);

        // Verify repository method invocations
        verify(storeRepository, times(1)).existsById("ST1");
        verify(storeRepository, times(1)).deleteById("ST1");
    }

    @Test
    void testDeleteStoreNotFound() {
        // Mock repository response
        when(storeRepository.existsById("ST1")).thenReturn(false);

        // Call the service method
        String result = storeService.deleteStore("ST1");

        // Verify the result
        assertNotNull(result);
        assertEquals("Store with id ST1 not found!", result);

        // Verify repository method invocations
        verify(storeRepository, times(1)).existsById("ST1");
        verify(storeRepository, never()).deleteById(any());
    }
}
