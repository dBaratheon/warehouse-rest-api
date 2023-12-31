package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findAllByCategory(String category);

    Page<Goods> findAll(Pageable pageable);

}