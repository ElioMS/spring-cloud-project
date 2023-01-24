package pe.chwa.delivery.http.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.chwa.delivery.model.dto.Product;
import pe.chwa.delivery.model.dto.ProductPatch;

@Component
@Slf4j
public class ProductClientFallback implements ProductClient {
    @Override
    public Product getProduct(Long id) {
        log.info("[Fallback] getProduct");
        return Product.builder()
                .name("none")
                .build();
    }



    @Override
    public void updateProductValues(Long id, ProductPatch body) {
    }
}
