package pe.chwa.product.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pe.chwa.product.model.entity.Category;
import pe.chwa.product.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(@Param("category") Category category);
}
