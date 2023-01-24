package pe.chwa.delivery.http.service;

import pe.chwa.delivery.http.request.DeliveryRequest;
import pe.chwa.delivery.model.entity.Header;

public interface DeliveryService {
    Header findAll();

    Header findDeliveryOrderById(Long id);
    Header createDeliveryOrder(DeliveryRequest deliveryRequest);
}
