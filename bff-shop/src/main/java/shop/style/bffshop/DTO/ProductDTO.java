package shop.style.bffshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;

    private String name;

    private String description;

    private Boolean active = true;

    private List<VariationDTO> variations;

}
