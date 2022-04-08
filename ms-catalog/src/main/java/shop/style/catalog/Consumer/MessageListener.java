package shop.style.catalog.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.style.catalog.Config.MQConfig;
import shop.style.catalog.DTO.RabbitMessage.VariationMessageDTO;
import shop.style.catalog.Entity.Variation;
import shop.style.catalog.Exception.VariationNotFoundException;
import shop.style.catalog.Repository.VariationRepository;

import java.util.List;

@Component
public class MessageListener {

    @Autowired
    private VariationRepository variationRepository;


    @RabbitListener (queues = MQConfig.CATALOG_QUEUE)
    public void messageListener(List<VariationMessageDTO> variationsMessagesDTO) {
        System.out.println(variationsMessagesDTO);

        variationsMessagesDTO.forEach(variationMessageDTO -> {
            Variation variation = variationRepository.findById(variationMessageDTO.getVariant_id())
                    .orElseThrow(() -> new VariationNotFoundException(variationMessageDTO.getVariant_id()));
            Integer quantity = variation.getQuantity() - variationMessageDTO.getQuantity();
            variation.setQuantity(quantity);
            variationRepository.save(variation);
        });
    }

}
