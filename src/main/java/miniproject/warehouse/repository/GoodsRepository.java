package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findAllByCategory(Category category);
}
