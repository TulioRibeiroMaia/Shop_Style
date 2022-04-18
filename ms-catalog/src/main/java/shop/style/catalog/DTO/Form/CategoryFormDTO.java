package shop.style.catalog.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFormDTO {

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotNull
    private Boolean active = true;
}
