package miniproject.warehouse.repository;

import miniproject.warehouse.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
    Page<Store> findAll(Pageable pageable);
}
