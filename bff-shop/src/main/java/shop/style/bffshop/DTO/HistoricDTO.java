package shop.style.bffshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricDTO {

    private CustomerDTO user;

    private List<PurchaseDTO> purchase;

}
