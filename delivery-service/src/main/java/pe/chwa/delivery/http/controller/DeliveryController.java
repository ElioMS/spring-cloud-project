package pe.chwa.delivery.http.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.chwa.delivery.http.request.DeliveryRequest;
import pe.chwa.delivery.http.service.DeliveryService;
import pe.chwa.delivery.model.entity.Header;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Header> getDeliveryOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(deliveryService.findDeliveryOrderById(id));
    }

    @PostMapping
    ResponseEntity<Header> createDeliveryOrder(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        Header header = deliveryService.createDeliveryOrder(deliveryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(header);
    }
}
