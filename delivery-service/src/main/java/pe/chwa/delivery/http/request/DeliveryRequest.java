package pe.chwa.delivery.http.request;

import lombok.Data;
import pe.chwa.delivery.model.entity.Order;

import java.util.List;

@Data
public class DeliveryRequest {
    private String customerName;
    private List<Order> orders;
}
