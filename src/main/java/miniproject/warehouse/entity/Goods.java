package miniproject.warehouse.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @Column(name = "goods_id")
    private String id;
    private String name;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String category;

    public Goods(){}

    public Goods(String id, String name, Timestamp createdAt, String category) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
