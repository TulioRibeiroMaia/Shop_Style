package shop.style.catalog.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFormDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Boolean active = true;

    @NotNull
    private List<@NotBlank String> category_ids;
}
