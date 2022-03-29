package shop.style.catalog.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFormDTO {

    @NotBlank
    @Size(min = 3, message = "Nome não possui caracteres suficientes")
    private String name;

    @NotBlank
    @Size(min = 5, message = "Descrição não possui caracteres suficientes")
    private String description;

    @NotNull
    private Boolean active;

    @NotNull
    private List<@NotBlank String> category_ids;
}
