package miniproject.warehouse.repository;

import miniproject.warehouse.entity.SupplyToWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplyToWarehouseRepository extends JpaRepository<SupplyToWarehouse, String> {
    Page<SupplyToWarehouse> findAll(Pageable pageable);
}
