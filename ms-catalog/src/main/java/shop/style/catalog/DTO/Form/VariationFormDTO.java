package shop.style.catalog.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.catalog.Enum.EnumSize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationFormDTO {

    @NotBlank
    @Size(min = 3)
    private String color;

    @NotNull
    private EnumSize size;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String product_id;
}
