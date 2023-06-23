package miniproject.warehouse.repository;

import miniproject.warehouse.entity.TransferToAnotherWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferToAnotherWarehouseRepository extends JpaRepository<TransferToAnotherWarehouse, String> {
}
