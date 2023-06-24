package miniproject.warehouse.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @Column(name = "warehouse_id")
    private String id;
    private String name;
    private String location;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Warehouse(){}

    public Warehouse(String id, String name, String location, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.createdAt = createdAt;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
