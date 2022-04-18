package shop.style.bffshop.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private PaymentDTO paymentMethod;

    private List<ProductDTO> products;

    private BigDecimal total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    private LocalDate date;
}
