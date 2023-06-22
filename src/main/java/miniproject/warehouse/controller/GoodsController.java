package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/goods")
    public Goods createGoods(@RequestBody Goods goods){
        return goodsService.createGoods(goods);
    }

    @GetMapping("/goods/{goodsId}")
    public Goods findByGoodsId(@PathVariable String goodsId){
        return goodsService.findByGoodsId(goodsId);
    }

    @GetMapping("/goods")
    public List<Goods> findAllGoods(){
        return goodsService.findAllGoods();
    }

    @PostMapping("/goods/{goodsId}")
    public Goods updateGoods(@PathVariable String goodsId, @RequestBody Goods goods){
        return goodsService.updateGoods(goodsId, goods);
    }

    @DeleteMapping("/goods/{goodsId}")
    String deleteGoods(@PathVariable String goodsId){
        return goodsService.deleteGoods(goodsId);
    }
}
