package shop.style.bffshop.Controller;

import org.springframework.web.bind.annotation.*;
import shop.style.bffshop.Client.CatalogClient;
import shop.style.bffshop.Client.CheckoutClient;
import shop.style.bffshop.Client.CustomerClient;
import shop.style.bffshop.Client.HistoryClient;
import shop.style.bffshop.DTO.*;
import shop.style.bffshop.DTO.Form.CustomerFormDTO;
import shop.style.bffshop.DTO.Form.PurchaseFormDTO;
import shop.style.bffshop.Service.TokenService;
import shop.style.bffshop.DTO.Form.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BffController {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private CheckoutClient checkoutClient;

    @Autowired
    private HistoryClient historyClient;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginFormDTO loginFormDTO) {
        UsernamePasswordAuthenticationToken dataLogin = loginFormDTO.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(dataLogin);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer "));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/users")
    public CustomerDTO saveCustomer(@RequestBody CustomerFormDTO body) {
        return customerClient.saveCustomer(body);
    }

    @GetMapping("/users/{id}")
    public CustomerDTO findCustomerById(@PathVariable Long id) {
        return customerClient.findCustomerByID(id);
    }

    @PutMapping("/users/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerFormDTO body) {
        return customerClient.updateCustomer(id, body);
    }

    @GetMapping("/products/{id}")
    public ProductDTO searchProductById(@PathVariable String id) {
        return catalogClient.searchProductById(id);
    }

    @GetMapping("/categories/{id}/products")
    public List<ProductDTO> getCategoryProduct(@PathVariable String id) {
        return catalogClient.getCategoryProduct(id);
    }

    @GetMapping("/payments")
    public List<PaymentDTO> findAllPayments() {
        return checkoutClient.findAllPayments();
    }

    @GetMapping("/purchases")
    void createPurchase(@RequestBody PurchaseFormDTO body) {
        checkoutClient.createPurchase(body);
    }

    @GetMapping("/historic/user/{userId}")
    public HistoricDTO findHistoricByUser(@PathVariable Long userId) {
        return historyClient.findHistoricByUser(userId);
    }
}