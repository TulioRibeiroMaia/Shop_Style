package shop.checkout.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.checkout.DTO.Form.CartFormDTO;
import shop.checkout.DTO.Form.PurchaseFormDTO;
import shop.checkout.DTO.RabbitMessage.PurchaseMessage;
import shop.checkout.Entity.Payment;
import shop.checkout.Exception.PaymentNotActiveException;
import shop.checkout.Exception.PaymentNotFoundException;
import shop.checkout.Exception.ProductNotActiveException;
import shop.checkout.Repository.PaymentRepository;
import shop.checkout.Service.PurchaseService;
import shop.checkout.DTO.CustomerActiveDTO;
import shop.checkout.DTO.ProductActiveDTO;
import shop.checkout.DTO.VariationMessage;
import shop.checkout.DTO.VariationProductDTO;
import shop.checkout.Client.CatalogClient;
import shop.checkout.Client.CustomerClient;
import shop.checkout.Exception.UserNotActiveException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RabbitMQServiceImpl rabbitMQService;

    @Override
    public void savePurchase(PurchaseFormDTO body) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        Optional<CustomerActiveDTO> customer = Optional.ofNullable(customerClient.findById(body.getUser_id()));

        if (!customer.get().getActive()) {
            throw new UserNotActiveException(body.getUser_id());
        }

        Optional<Payment> payment = Optional.ofNullable(paymentRepository.findById(body.getPayment_id()).orElseThrow(() -> new PaymentNotFoundException(body.getPayment_id())));

        if (!payment.get().getStatus()) {
            throw new PaymentNotActiveException(payment.get().getId());
        }

        List<CartFormDTO> cartFormDTO = body.getCart();

        for (CartFormDTO items : cartFormDTO) {

            VariationProductDTO variationProductDTO = catalogClient.searchById(items.getVariant_id());
            Optional<ProductActiveDTO> productActiveDTO = Optional.ofNullable(catalogClient.findById(variationProductDTO.getProduct_id()));
            if (!productActiveDTO.get().getActive()) {
                throw new ProductNotActiveException(productActiveDTO.get().getId());
            }

            BigDecimal itemPrice = variationProductDTO.getPrice().multiply(BigDecimal.valueOf(items.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }

        List<VariationMessage> variationMessage = cartFormDTO.stream()
                .map(items -> modelMapper.map(items, VariationMessage.class)).toList();

        rabbitMQService.publishMessageInCatalog(variationMessage);

        PurchaseMessage purchaseMessage = new PurchaseMessage(
                body.getUser_id(), body.getPayment_id(),
                totalPrice, LocalDate.now(),
                variationMessage
        );

        rabbitMQService.publishMessageInHistory(purchaseMessage);
    }

}
