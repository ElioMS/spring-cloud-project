package pe.chwa.product.http.service;

import pe.chwa.product.model.ProductRequest;
import pe.chwa.product.model.entity.Category;
import pe.chwa.product.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> listAllProduct(Long id);
    Product findProductById(Long id);
    Product createProduct(ProductRequest product);
    Product updateProduct(Long id, Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double stock);
}
