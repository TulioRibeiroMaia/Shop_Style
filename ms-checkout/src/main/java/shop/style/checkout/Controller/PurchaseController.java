package shop.style.checkout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.style.checkout.DTO.Form.PurchaseFormDTO;
import shop.style.checkout.Service.PurchaseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPurchase(@RequestBody @Valid PurchaseFormDTO body){
        purchaseService.savePurchase(body);
    }
}
