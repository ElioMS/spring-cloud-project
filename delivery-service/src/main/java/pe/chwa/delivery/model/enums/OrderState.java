package pe.chwa.delivery.model.enums;

import lombok.Getter;

@Getter
public enum OrderState {
    CREATED("CREATED"),
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CANCELED("CANCELED");

    private final String value;

    OrderState(String value) {
        this.value = value;
    }
}
