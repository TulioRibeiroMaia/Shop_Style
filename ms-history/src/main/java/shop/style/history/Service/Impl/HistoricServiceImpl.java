package shop.style.history.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.history.Client.CatalogClient;
import shop.style.history.Client.CheckoutClient;
import shop.style.history.Client.CustomerClient;
import shop.style.history.DTO.*;
import shop.style.history.DTO.RabbitMessage.PurchaseRabbitMessageDTO;
import shop.style.history.DTO.RabbitMessage.VariationMessageDTO;
import shop.style.history.Entity.Historic;
import shop.style.history.Entity.Purchase;
import shop.style.history.Entity.Variation;
import shop.style.history.Exception.HistoricNotFoundException;
import shop.style.history.Repository.HistoricRepository;
import shop.style.history.Repository.PurchaseRepository;
import shop.style.history.Repository.VariationRepository;
import shop.style.history.Service.HistoricServive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricServiceImpl implements HistoricServive {

    @Autowired
    private HistoricRepository historicRepository;

    @Autowired
    private VariationRepository variationRepository;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private CheckoutClient checkoutClient;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HistoricDTO findHistoricByUser(Long userId) {
        List<PurchaseDTO> purchasesDTO = new ArrayList<>();
        Historic historic = historicRepository.findByUserId(userId)
                .orElseThrow(() -> new HistoricNotFoundException(userId));

        CustomerDTO customerDTO = customerClient.findCustomerById(historic.getUserId());

        List<Purchase> purchases = historic.getPurchases();

        for (Purchase purchase : purchases){
            PaymentDTO paymentDTO = checkoutClient.findById(purchase.getPaymentId());
            List<Variation> variations = purchase.getVariations();
            BigDecimal valueTotal = purchase.getTotal();
            LocalDate localDate = purchase.getDate();

            List<ProductDTO> products = variations.stream()
                    .map(variation -> {
                        VariationCustomerDTO variationCustomerDTO = catalogClient.searchVariation(variation.getVariant_id());
                        ProductDTO productDTO = catalogClient.searchProductById(variationCustomerDTO.getProduct_id());
                        productDTO.setColor(variationCustomerDTO.getColor());
                        productDTO.setSize(variationCustomerDTO.getSize());
                        productDTO.setQuantity(variationCustomerDTO.getQuantity());
                        productDTO.setPrice(variationCustomerDTO.getPrice());
                        System.out.println(productDTO);
                        return productDTO;
                    }).toList();

            purchasesDTO.add(new PurchaseDTO(paymentDTO, products, valueTotal, localDate));
        }

        return new HistoricDTO(customerDTO, purchasesDTO);
    }

    @Override
    public void  saveANewPurchase(PurchaseRabbitMessageDTO body) {

        Optional<Historic> historic = historicRepository.findByUserId(body.getUser_id());
        if (historic.isPresent()) {
            Purchase savedInPurchase = modelMapper.map(body, Purchase.class);

            List<Variation> variationList = savedInPurchase.getVariations();
            List<Variation> savedVariations = variationList.stream().map(variation -> variationRepository.save(variation)).collect(Collectors.toList());

            savedInPurchase.setVariations(savedVariations);
            Purchase savedPurchase = purchaseRepository.save(savedInPurchase);

            historic.get().getPurchases().add(savedPurchase);
            historicRepository.save(historic.get());
        } else {
            Historic historic1 = new Historic();
            historic1.setUserId(body.getUser_id());

            List<VariationMessageDTO> variations = body.getVariations();
            List<Variation> variationList = variations.stream().map(variation -> {
                Variation map = modelMapper.map(variation, Variation.class);

                Variation savedvariation = variationRepository.save(map);
                return savedvariation;
            }).toList();

            Purchase savedPurchaseInHistoric = new Purchase();

            savedPurchaseInHistoric.setPaymentId(body.getPayment_id());
            savedPurchaseInHistoric.setTotal(body.getTotal());
            savedPurchaseInHistoric.setDate(body.getDate());
            savedPurchaseInHistoric.setVariations(variationList);

            Purchase purchasesaved = purchaseRepository.save(savedPurchaseInHistoric);

            historic1.getPurchases().add(purchasesaved);
            historicRepository.save(historic1);
        }
    }
}
