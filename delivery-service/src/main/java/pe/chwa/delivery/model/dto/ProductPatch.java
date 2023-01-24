package pe.chwa.delivery.model.dto;

import lombok.Data;
import pe.chwa.delivery.model.enums.ActionsEnum;

@Data
public class ProductPatch {
    private ActionsEnum action;
    private String key;
    private String value;
}