package miniproject.warehouse.repository;

import miniproject.warehouse.entity.WarehouseToStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseToStoreRepository extends JpaRepository<WarehouseToStore, Long> {
}
