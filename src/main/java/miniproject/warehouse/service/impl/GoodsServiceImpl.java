package miniproject.warehouse.service.impl;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.entity.enums.Category;
import miniproject.warehouse.exception.BadRequestException;
import miniproject.warehouse.exception.NotFoundException;
import miniproject.warehouse.repository.GoodsRepository;
import miniproject.warehouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public Goods createGoods(Goods goods) {
        if (!StringUtils.hasText(goods.getName())) {
            throw new BadRequestException("Name must be filled");
        }
        if (goods.getCategory() == null) {
            throw new BadRequestException("Category can't be null");
        }
        goods.setId("GD" + goodsRepository.count());
        goods.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return goodsRepository.save(goods);
    }

    @Override
    public Goods findByGoodsId(String goodsId) {
        return goodsRepository.findById(goodsId).orElseThrow(() -> new NotFoundException("Goods with id " + goodsId + " not found"));
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods updateGoods(String goodsId, Goods goods) {
        Goods original = findByGoodsId(goodsId);
        if (goods.getName() != null) {
            original.setName(goods.getName());
        }
        original.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return goodsRepository.save(original);
    }

    @Override
    public String deleteGoods(String goodsId) {
        if (goodsRepository.existsById(goodsId)) {
            goodsRepository.deleteById(goodsId);
            return "Goods with id " + goodsId + " deleted";
        } else {
            return "Goods with id " + goodsId + " not found";
        }
    }

    @Override
    public List<Goods> findAllByCategory(Category category) {
        return goodsRepository.findAllByCategory(category);
    }
}
