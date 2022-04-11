package shop.style.history.DTO.RabbitMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationMessageDTO {

    private String variant_id;

    private Integer quantity;
}