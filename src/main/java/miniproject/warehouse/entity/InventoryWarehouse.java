package miniproject.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "inventory_warehouses")
public class InventoryWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Warehouse warehouse;

    public InventoryWarehouse() {
    }

    public InventoryWarehouse(Long quantity, Timestamp lastUpdated, Goods goods, Warehouse warehouse) {
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.goods = goods;
        this.warehouse = warehouse;
    }

    public InventoryWarehouse(Long id, Long quantity, Timestamp lastUpdated, Goods goods, Warehouse warehouse) {
        this.id = id;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.goods = goods;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
