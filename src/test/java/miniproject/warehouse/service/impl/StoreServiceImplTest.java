package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Store;
import miniproject.warehouse.repository.StoreRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void createStore_ValidInput_ReturnsCreatedStore() {
        Store store = new Store();
        store.setName("Test Store");
        store.setLocation("Test Location");

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        Store createdStore = storeService.createStore(store);

        assertNotNull(createdStore);
        assertNotNull(createdStore.getId());
        assertEquals(store.getName(), createdStore.getName());
        assertEquals(store.getLocation(), createdStore.getLocation());
        assertNotNull(createdStore.getCreatedAt());
        verify(storeRepository, times(1)).save(store);
    }
}