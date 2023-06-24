package miniproject.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "warehouse_to_store")
public class WarehouseToStore {
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
    @JoinColumn(name = "storeid_dst", referencedColumnName = "store_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Store storeDst;


    public WarehouseToStore() {
    }

    public WarehouseToStore(Long quantity, Timestamp createdAt) {
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public WarehouseToStore(Long id, Long quantity, Timestamp createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
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

    public Warehouse getWarehouseSrc() {
        return warehouseSrc;
    }

    public void setWarehouseSrc(Warehouse warehouseSrc) {
        this.warehouseSrc = warehouseSrc;
    }

    public Store getStoreDst() {
        return storeDst;
    }

    public void setStoreDst(Store storeDst) {
        this.storeDst = storeDst;
    }
}
