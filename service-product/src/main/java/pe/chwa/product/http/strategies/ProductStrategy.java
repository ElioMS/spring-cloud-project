package pe.chwa.product.http.strategies;

import pe.chwa.product.model.entity.Category;
import pe.chwa.product.model.entity.Product;
import pe.chwa.product.model.repository.ProductRepository;

import java.util.List;

public enum ProductStrategy {

    FIND_ALL {
        @Override
        public List<Product> execute(ProductRepository productRepository, Long id) {
            return productRepository.findAll();
        }
    },
    FIND_BY_CATEGORY {
        @Override
        public List<Product> execute(ProductRepository productRepository, Long id) {
            Category category = Category.builder().id(id).build();
            return productRepository.findByCategory(category);
        }
    };

    public abstract List<Product> execute(ProductRepository productRepository, Long id);
}
