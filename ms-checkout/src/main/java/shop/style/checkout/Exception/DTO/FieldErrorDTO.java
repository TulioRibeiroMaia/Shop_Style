package shop.style.checkout.Exception.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDTO {

    private String field;

    private String error;
}
