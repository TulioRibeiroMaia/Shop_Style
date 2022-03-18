package shop.style.customer.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.style.customer.Exception.DTO.FieldErrorDTO;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class FieldErrorMessage {
    private int statusCode;

    private Date timestamp;

    private List<FieldErrorDTO> fieldErrors;

    private String description;
}
