package shop.style.checkout.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {

    CREDIT_CARD("credit card"),
    DEBIT_CARD("debit card"),
    CASH("cash"),
    PIX("pix");

    private String value;

    PaymentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static PaymentType forValues(@JsonProperty("type") String value) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.value.equalsIgnoreCase(value)) {
                return paymentType;
            }
        }
        return null;
    }
}
