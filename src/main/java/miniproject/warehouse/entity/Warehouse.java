package miniproject.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
