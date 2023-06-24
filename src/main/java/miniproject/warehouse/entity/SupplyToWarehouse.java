package miniproject.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "supply_to_warehouse")
public class SupplyToWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whid_destination", referencedColumnName = "warehouse_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Warehouse warehouse;

    public SupplyToWarehouse(){}

    public SupplyToWarehouse(Long quantity, Timestamp createdAt, Goods goods, Warehouse warehouse) {
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.goods = goods;
        this.warehouse = warehouse;
    }

    public SupplyToWarehouse(Long id, Long quantity, Timestamp createdAt, Goods goods, Warehouse warehouse) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
