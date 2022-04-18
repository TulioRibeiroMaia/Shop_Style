package shop.checkout.Service.Impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.checkout.Config.MQConfig;
import shop.checkout.DTO.RabbitMessage.PurchaseMessage;
import shop.checkout.DTO.VariationMessage;

import java.util.List;

@Service
public class RabbitMQServiceImpl {

    @Autowired
    private RabbitTemplate template;

    public void publishMessageInCatalog(List<VariationMessage> variationMessage) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.CATALOG_ROUTING_KEY, variationMessage);
    }

    public void publishMessageInHistory(PurchaseMessage purchaseMessage) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.HISTORY_ROUTING_KEY, purchaseMessage);
    }
}
