package pe.chwa.product.model.dto;

import lombok.Data;
import pe.chwa.product.model.enums.ActionsEnum;

@Data
public class ProductPatchDto {
    private ActionsEnum action;
    private String key;
    private String value;
}
