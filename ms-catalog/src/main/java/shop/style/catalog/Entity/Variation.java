package shop.style.catalog.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import shop.style.catalog.Enum.EnumSize;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Variation {

    @Id
    private Long id;

    private String color;

    private EnumSize size;

    private BigDecimal price;

    private Integer quantity;


}
