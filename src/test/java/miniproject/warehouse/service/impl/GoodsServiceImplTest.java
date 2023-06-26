package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.repository.GoodsRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodsServiceImplTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsServiceImpl goodsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createGoodsValidInputReturnsCreatedGoods() {
        Goods goods = new Goods();
        goods.setName("Test Goods");
        goods.setCategory("Test Category");

        when(goodsRepository.save(any(Goods.class))).thenReturn(goods);
        Goods createdGoods = goodsService.createGoods(goods);

        //Test
        assertNotNull(createdGoods);
        assertNotNull(createdGoods.getId());
        assertEquals(goods.getName(), createdGoods.getName());
        assertEquals(goods.getCategory(), createdGoods.getCategory());
        assertNotNull(createdGoods.getCreatedAt());
        verify(goodsRepository, times(1)).save(goods);
    }
}
