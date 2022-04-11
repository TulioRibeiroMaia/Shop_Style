package shop.style.history.DTO.RabbitMessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRabbitMessageDTO {

    private Long userId;

    private Long paymentId;

    private BigDecimal total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    private List<VariationMessageDTO> variations = new ArrayList<>();
}
