package miniproject.warehouse.controller;

import miniproject.warehouse.entity.Goods;
import miniproject.warehouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public Goods createGoods(@RequestBody Goods goods){
        return goodsService.createGoods(goods);
    }

    @GetMapping("/{goodsId}")
    public Goods findByGoodsId(@PathVariable String goodsId){
        return goodsService.findByGoodsId(goodsId);
    }

    @GetMapping
    public ResponseEntity<Page<Goods>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "5") int pageSize) {
        Page<Goods> page = goodsService.findAllRecord(pageNo, pageSize);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{goodsId}")
    public Goods updateGoods(@PathVariable String goodsId, @RequestBody Goods goods){
        return goodsService.updateGoods(goodsId, goods);
    }

    @DeleteMapping("/{goodsId}")
    String deleteGoods(@PathVariable String goodsId){
        return goodsService.deleteGoods(goodsId);
    }

    @GetMapping("/category/{category}")
    public List<Goods> findAllBycategory(@PathVariable String category){
        return goodsService.findAllByCategory(category);
    }
}
