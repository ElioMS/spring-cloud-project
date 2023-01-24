package pe.chwa.delivery.http.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.chwa.delivery.model.dto.Product;
import pe.chwa.delivery.model.dto.ProductPatch;

@FeignClient(name = "product-service", path = "/products", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/{id}")
    Product getProduct(@PathVariable("id") Long id);



    @PutMapping("/{id}/stock")
    void updateProductValues(@PathVariable("id") Long id, @RequestBody ProductPatch body);
}
