package miniproject.warehouse.entity;

import miniproject.warehouse.entity.enums.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class GoodsTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String id = "12345";
        String name = "Test Goods";
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Category category = Category.DRINK;

        // Act
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(name);
        goods.setCreatedAt(createdAt);
        goods.setCategory(category);

        // Assert
        Assertions.assertEquals(id, goods.getId());
        Assertions.assertEquals(name, goods.getName());
        Assertions.assertEquals(createdAt, goods.getCreatedAt());
        Assertions.assertEquals(category, goods.getCategory());
    }

    @Test
    public void testConstructorWithParameters() {
        // Arrange
        String id = "12345";
        String name = "Test Goods";
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        // Act
        Goods goods = new Goods(id, name, createdAt);

        // Assert
        Assertions.assertEquals(id, goods.getId());
        Assertions.assertEquals(name, goods.getName());
        Assertions.assertEquals(createdAt, goods.getCreatedAt());
    }
}
