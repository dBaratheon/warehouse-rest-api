package miniproject.warehouse.repository;

import miniproject.warehouse.entity.WarehouseToStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseToStoreRepository extends JpaRepository<WarehouseToStore, Long> {
    Page<WarehouseToStore> findAll(Pageable pageable);
}
