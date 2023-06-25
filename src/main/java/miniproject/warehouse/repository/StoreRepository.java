package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
}
