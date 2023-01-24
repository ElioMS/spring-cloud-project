package pe.chwa.delivery.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double stock;
    private String status;
    private Date createdAt;
    private Category category;
}
