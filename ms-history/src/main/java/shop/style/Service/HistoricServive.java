package shop.style.Service;

import shop.style.DTO.RabbitMessage.PurchaseRabbitMessageDTO;
import shop.style.DTO.HistoricDTO;

public interface HistoricServive {

    HistoricDTO findHistoricByUser(Long userId);

    void saveANewPurchase(PurchaseRabbitMessageDTO purchaseRabbitMessageDTO);
}
