package shop.style.customer.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {

    FEMININO("Feminino"),

    MASCULINO("Masculino");

    private String value;

    Sex(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Sex forValues(@JsonProperty("sex") String value) {
        for (Sex sex : Sex.values()) {
            if (sex.value.equalsIgnoreCase(value)) {
                return sex;
            }
        }
        return null;
    }
}
