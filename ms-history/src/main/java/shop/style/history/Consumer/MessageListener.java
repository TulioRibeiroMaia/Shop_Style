package shop.style.history.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.style.history.Config.MQConfig;
import shop.style.history.DTO.RabbitMessage.PurchaseRabbitMessageDTO;
import shop.style.history.Service.HistoricServive;

@Component
public class MessageListener {

    @Autowired
    private HistoricServive historicServive;

    @RabbitListener(queues = MQConfig.HISTORY_QUEUE)
    public void messageListener(PurchaseRabbitMessageDTO purchaseRabbitMessageDTO) {
        historicServive.saveANewPurchase(purchaseRabbitMessageDTO);
    }
}
