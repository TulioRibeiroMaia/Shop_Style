package shop.style.history.Service;

import shop.style.history.DTO.HistoricDTO;
import shop.style.history.DTO.RabbitMessage.PurchaseRabbitMessageDTO;

public interface HistoricServive {

    HistoricDTO findHistoricByUser(Long userId);

    void saveANewPurchase(PurchaseRabbitMessageDTO purchaseRabbitMessageDTO);
}
