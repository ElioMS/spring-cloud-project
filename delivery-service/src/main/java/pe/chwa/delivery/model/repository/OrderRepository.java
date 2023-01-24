package pe.chwa.delivery.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.chwa.delivery.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
