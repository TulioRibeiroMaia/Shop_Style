package shop.style.checkout.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.checkout.Client.CatalogClient;
import shop.style.checkout.Client.CustomerClient;
import shop.style.checkout.DTO.CustomerActiveDTO;
import shop.style.checkout.DTO.Form.CartFormDTO;
import shop.style.checkout.DTO.Form.PurchaseFormDTO;
import shop.style.checkout.DTO.ProductActiveDTO;
import shop.style.checkout.DTO.VariationMessage;
import shop.style.checkout.DTO.VariationProductDTO;
import shop.style.checkout.Entity.Payment;
import shop.style.checkout.Exception.PaymentNotActiveException;
import shop.style.checkout.Exception.PaymentNotFoundException;
import shop.style.checkout.Exception.ProductNotActiveException;
import shop.style.checkout.Exception.UserNotActiveException;
import shop.style.checkout.Repository.PaymentRepository;
import shop.style.checkout.Service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;

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

        CustomerActiveDTO customer = customerClient.findById(body.getUser_id());

        if (!customer.getActive()) {
            throw new UserNotActiveException(body.getUser_id());
        }
        Payment payment = paymentRepository.findById(body.getPayment_id()).orElseThrow(() -> new PaymentNotFoundException(body.getPayment_id()));

        System.out.println(payment);
        if (!payment.getStatus()) {
            System.out.println(payment.getStatus());
            throw new PaymentNotActiveException(payment.getId());
        }

        List<CartFormDTO> cartFormDTO = body.getCart();

        for (CartFormDTO items : cartFormDTO) {

            VariationProductDTO variationProductDTO = catalogClient.searchById(items.getVariant_id());
            ProductActiveDTO productActiveDTO = catalogClient.findById(variationProductDTO.getProduct_id());
            if (!productActiveDTO.getActive()) {
                throw new ProductNotActiveException(productActiveDTO.getId());
            }

            BigDecimal itemPrice = variationProductDTO.getPrice().multiply(BigDecimal.valueOf(items.getQuantity()));
            totalPrice = totalPrice.add(itemPrice);
        }

        List<VariationMessage> variationMessage = cartFormDTO.stream()
                .map(items -> modelMapper.map(items, VariationMessage.class)).toList();

        rabbitMQService.publishMessageInCatalog(variationMessage);
    }

}
