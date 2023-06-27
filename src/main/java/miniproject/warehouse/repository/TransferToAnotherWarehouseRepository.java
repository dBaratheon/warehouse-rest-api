package miniproject.warehouse.repository;

import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferToAnotherWarehouseRepository extends JpaRepository<TransferToAnotherWarehouse, String> {
    Page<TransferToAnotherWarehouse> findAll(Pageable pageable);
}
