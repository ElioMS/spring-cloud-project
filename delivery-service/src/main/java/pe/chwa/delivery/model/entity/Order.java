package pe.chwa.delivery.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import pe.chwa.delivery.model.dto.Product;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    @Transient
    private Product product;

}
