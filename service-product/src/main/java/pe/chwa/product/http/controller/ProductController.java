package pe.chwa.product.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.chwa.product.model.dto.GenericResponse;
import pe.chwa.product.model.dto.ProductPatchDto;
import pe.chwa.product.model.entity.Product;
import pe.chwa.product.model.ProductRequest;
import pe.chwa.product.http.service.IProductService;
import pe.chwa.product.model.enums.ActionsEnum;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(
            @RequestParam(name = "category", required = false) Long categoryId
    ) {
        List<Product> products = productService.listAllProduct(categoryId);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @RequestBody Product product) {
        Product productDb = productService.updateProduct(id, product);
        return ResponseEntity.ok(productDb);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<?> updateProductValues(@PathVariable("id") Long id,
                                                 @RequestBody ProductPatchDto body) {
        // Cause feign doesn't accept patch mapping
        if (body.getAction() == ActionsEnum.UPDATE_STOCK) {
            productService.updateStock(id, Double.parseDouble(body.getValue()));
        }

        GenericResponse response = new GenericResponse();
        response.setMessage("The product has been updated");
        response.setValue("Action: "+body.getAction()+"- Key: "+body.getKey()+"- Value: "+body.getValue());

        return ResponseEntity.ok(response);
    }
}
