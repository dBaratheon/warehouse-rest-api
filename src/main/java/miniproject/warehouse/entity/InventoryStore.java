package miniproject.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "inventory_stores")
public class InventoryStore {
    @Id
    private String id;
    private Long quantity;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Store store;

    public InventoryStore(){}

    public InventoryStore(Long quantity, Timestamp lastUpdated, Goods goods, Store store) {
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.goods = goods;
        this.store = store;
    }

    public InventoryStore(String id, Long quantity, Timestamp lastUpdated, Goods goods, Store store) {
        this.id = id;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.goods = goods;
        this.store = store;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
