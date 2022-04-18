package shop.checkout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import shop.checkout.DTO.Form.PaymentFormDTO;
import shop.checkout.DTO.PaymentDTO;
import shop.checkout.Service.PaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDTO> savePayment(@RequestBody @Valid PaymentFormDTO body) {
        PaymentDTO payment = this.paymentService.savePayment(body);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PaymentDTO> findAllPayments() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
        PaymentDTO payment = this.paymentService.findById(id);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody @Valid PaymentFormDTO body) {
        PaymentDTO payment = this.paymentService.updatePayment(id, body);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        this.paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
