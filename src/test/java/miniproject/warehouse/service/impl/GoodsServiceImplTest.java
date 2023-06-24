package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.enums.Category;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void createGoods_ValidInput_ReturnsCreatedGoods() {
        Goods goods = new Goods();
        goods.setName("Test Goods");
        goods.setCategory(Category.ELECTRONIC);

        Mockito.when(goodsRepository.count()).thenReturn(0L);
        Mockito.when(goodsRepository.save(Mockito.any(Goods.class))).thenReturn(goods);

        Goods createdGoods = goodsService.createGoods(goods);

        Assertions.assertEquals(goods.getName(), createdGoods.getName());
        Assertions.assertEquals(goods.getCategory(), createdGoods.getCategory());
        Assertions.assertNotNull(createdGoods.getId());
        Assertions.assertNotNull(createdGoods.getCreatedAt());
    }

    @Test
    void createGoods_EmptyName_ThrowsBadRequestException() {
        Goods goods = new Goods();
        goods.setCategory(Category.ELECTRONIC);

        Assertions.assertThrows(BadRequestException.class, () -> goodsService.createGoods(goods));
    }

    @Test
    void createGoods_NullCategory_ThrowsBadRequestException() {
        Goods goods = new Goods();
        goods.setName("Test Goods");

        Assertions.assertThrows(BadRequestException.class, () -> goodsService.createGoods(goods));
    }

    @Test
    void findByGoodsId_ExistingId_ReturnsCorrespondingGoods() {
        String goodsId = "GD1";
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setName("Test Goods");
        goods.setCategory(Category.ELECTRONIC);

        Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.of(goods));

        Goods foundGoods = goodsService.findByGoodsId(goodsId);

        Assertions.assertEquals(goodsId, foundGoods.getId());
        Assertions.assertEquals(goods.getName(), foundGoods.getName());
        Assertions.assertEquals(goods.getCategory(), foundGoods.getCategory());
    }

    @Test
    void findByGoodsId_NonExistingId_ThrowsNotFoundException() {
        String goodsId = "GD1";

        Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> goodsService.findByGoodsId(goodsId));
    }

    @Test
    void findAllGoods_ReturnsAllGoods() {
        Goods goods1 = new Goods();
        goods1.setId("GD1");
        goods1.setName("Goods 1");
        goods1.setCategory(Category.ELECTRONIC);

        Goods goods2 = new Goods();
        goods2.setId("GD2");
        goods2.setName("Goods 2");
        goods2.setCategory(Category.DRINK);

        List<Goods> allGoods = Arrays.asList(goods1, goods2);

        Mockito.when(goodsRepository.findAll()).thenReturn(allGoods);

        List<Goods> foundGoods = goodsService.findAllGoods();

        Assertions.assertEquals(allGoods.size(), foundGoods.size());
        Assertions.assertEquals(allGoods.get(0).getId(), foundGoods.get(0).getId());
        Assertions.assertEquals(allGoods.get(1).getId(), foundGoods.get(1).getId());
        // ... and so on for other attributes
    }

    @Test
    void updateGoods_ExistingGoodsId_ReturnsUpdatedGoods() {
        String goodsId = "GD1";
        Goods originalGoods = new Goods();
        originalGoods.setId(goodsId);
        originalGoods.setName("Original Goods");
        originalGoods.setCategory(Category.ELECTRONIC);

        Goods updatedGoods = new Goods();
        updatedGoods.setName("Updated Goods");

        Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.of(originalGoods));
        Mockito.when(goodsRepository.save(Mockito.any(Goods.class))).thenReturn(updatedGoods);

        Goods result = goodsService.updateGoods(goodsId, updatedGoods);

        Assertions.assertEquals(updatedGoods.getName(), result.getName());
        // ... and so on for other attributes
    }

    @Test
    void updateGoods_NonExistingGoodsId_ThrowsNotFoundException() {
        String goodsId = "GD1";
        Goods updatedGoods = new Goods();
        updatedGoods.setName("Updated Goods");

        Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> goodsService.updateGoods(goodsId, updatedGoods));
    }

    @Test
    void deleteGoods_ExistingGoodsId_ReturnsSuccessMessage() {
        String goodsId = "GD1";

        Mockito.when(goodsRepository.existsById(goodsId)).thenReturn(true);

        String result = goodsService.deleteGoods(goodsId);

        Assertions.assertEquals("Goods with id " + goodsId + " deleted", result);
        Mockito.verify(goodsRepository, Mockito.times(1)).deleteById(goodsId);
    }

    @Test
    void deleteGoods_NonExistingGoodsId_ReturnsNotFoundMessage() {
        String goodsId = "GD1";

        Mockito.when(goodsRepository.existsById(goodsId)).thenReturn(false);

        String result = goodsService.deleteGoods(goodsId);

        Assertions.assertEquals("Goods with id " + goodsId + " not found", result);
        Mockito.verify(goodsRepository, Mockito.never()).deleteById(Mockito.anyString());
    }

    @Test
    void findAllByCategory_ReturnsGoodsWithMatchingCategory() {
        Category category = Category.ELECTRONIC;

        Goods goods1 = new Goods();
        goods1.setId("GD1");
        goods1.setName("Goods 1");
        goods1.setCategory(category);

        Goods goods2 = new Goods();
        goods2.setId("GD2");
        goods2.setName("Goods 2");
        goods2.setCategory(category);

        List<Goods> matchingGoods = Arrays.asList(goods1, goods2);

        Mockito.when(goodsRepository.findAllByCategory(category)).thenReturn(matchingGoods);

        List<Goods> foundGoods = goodsService.findAllByCategory(category);

        Assertions.assertEquals(matchingGoods.size(), foundGoods.size());
        Assertions.assertEquals(matchingGoods.get(0).getId(), foundGoods.get(0).getId());
        Assertions.assertEquals(matchingGoods.get(1).getId(), foundGoods.get(1).getId());
        // ... and so on for other attributes
    }
}
