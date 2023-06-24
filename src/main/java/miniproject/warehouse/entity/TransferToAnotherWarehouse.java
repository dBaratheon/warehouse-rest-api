package miniproject.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "warehouse_to_warehouse")
public class TransferToAnotherWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrhid_src", referencedColumnName = "warehouse_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Warehouse warehouseSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrhid_dst", referencedColumnName = "warehouse_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Warehouse warehouseDst;

    public TransferToAnotherWarehouse(){}

    public TransferToAnotherWarehouse(Timestamp createdAt, Goods goods, Warehouse warehouseSrc, Warehouse warehouseDst) {
        this.createdAt = createdAt;
        this.goods = goods;
        this.warehouseSrc = warehouseSrc;
        this.warehouseDst = warehouseDst;
    }

    public TransferToAnotherWarehouse(Long id, Timestamp createdAt, Goods goods, Warehouse warehouseSrc, Warehouse warehouseDst) {
        this.id = id;
        this.createdAt = createdAt;
        this.goods = goods;
        this.warehouseSrc = warehouseSrc;
        this.warehouseDst = warehouseDst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Warehouse getWarehouseSrc() {
        return warehouseSrc;
    }

    public void setWarehouseSrc(Warehouse warehouseSrc) {
        this.warehouseSrc = warehouseSrc;
    }

    public Warehouse getWarehouseDst() {
        return warehouseDst;
    }

    public void setWarehouseDst(Warehouse warehouseDst) {
        this.warehouseDst = warehouseDst;
    }
}
