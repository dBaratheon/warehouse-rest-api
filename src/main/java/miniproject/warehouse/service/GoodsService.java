package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {
    Goods createGoods(Goods goods);
    Goods findByGoodsId(String goodsId);
    Page<Goods> findAllRecord(int pageNo, int pageSize);
    Goods updateGoods(String goodsId, Goods goods);
    String deleteGoods(String goodsId);
    List<Goods> findAllByCategory(String category);
}