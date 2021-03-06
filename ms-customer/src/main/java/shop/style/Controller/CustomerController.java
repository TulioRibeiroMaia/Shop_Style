package shop.style.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.style.DTO.CustomerDTO;
import shop.style.DTO.FormDTO.CustomerFormDTO;
import shop.style.Service.CustomerService;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/users")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody @Valid CustomerFormDTO body) {
        CustomerDTO customer = this.service.saveCustomer(body);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long id) {
        CustomerDTO customer = this.service.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerFormDTO body) {
        CustomerDTO customer = this.service.updateCustomer(id, body);
        return ResponseEntity.ok(customer);
    }
}
