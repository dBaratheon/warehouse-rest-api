package miniproject.warehouse.service;

import miniproject.warehouse.entity.Goods;

import java.util.List;

public interface GoodsService {
    Goods createGoods(Goods goods);
    Goods findByGoodsId(String goodsId);
    List<Goods> findAllGoods();
    Goods updateGoods(String goodsId, Goods goods);
    String deleteGoods(String goodsId);
    List<Goods> findAllByCategory(String category);
}