package pe.chwa.product.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double stock;
    private Long categoryId;
    private String status;
    private Date createdAt;
}
