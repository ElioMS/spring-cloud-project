package pe.chwa.product.http.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.chwa.product.http.strategies.ProductStrategy;
import pe.chwa.product.model.ProductRequest;
import pe.chwa.product.model.entity.Category;
import pe.chwa.product.model.entity.Product;
import pe.chwa.product.model.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProduct(Long id) {
        ProductStrategy searchStrategy = ProductStrategy.FIND_ALL;

        if (id != null) {
            searchStrategy = ProductStrategy.FIND_BY_CATEGORY;
        }

        return searchStrategy.execute(productRepository, id);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductRequest product) {
        product.setStatus("CREATED");
        product.setCreatedAt(new Date());

         Product productB = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .stock(product.getStock())
                .category(Category.builder().id(product.getCategoryId()).build())
                .build();

        return productRepository.save(productB);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productDb = verifyIfProductExists(id);

        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setStock(product.getStock());
        productDb.setStatus(product.getStatus());
        return productRepository.save(productDb);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDb = verifyIfProductExists(id);
        productDb.setStatus("DELETED");
        return productRepository.save(productDb);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double stock) {
        Product productDb = verifyIfProductExists(id);
        Double newStockValue = productDb.getStock() + stock;
        productDb.setStock(newStockValue);
        return productRepository.save(productDb);
    }

    private Product verifyIfProductExists(Long id) {
        Product product = findProductById(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "The product doesn't exists");
        }

        return findProductById(id);
    }
}
