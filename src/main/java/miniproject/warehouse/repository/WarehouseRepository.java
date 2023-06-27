package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    Page<Warehouse> findAll(Pageable pageable);
}
