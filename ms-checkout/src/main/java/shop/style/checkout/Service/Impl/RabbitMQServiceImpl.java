package shop.style.checkout.Service.Impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.checkout.Config.MQConfig;
import shop.style.checkout.DTO.VariationMessage;

import java.util.List;

@Service
public class RabbitMQServiceImpl {

    @Autowired
    private RabbitTemplate template;

    public void publishMessageInCatalog(List<VariationMessage> variationMessage) {
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.CATALOG_ROUTING_KEY, variationMessage);
    }
}
