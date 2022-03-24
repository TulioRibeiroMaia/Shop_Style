package shop.style.customer.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.customer.DTO.CustomerDTO;
import shop.style.customer.DTO.FormDTO.CustomerFormDTO;
import shop.style.customer.Entity.Customer;
import shop.style.customer.Repository.CustomerRepository;
import shop.style.customer.Service.CustomerService;
import shop.style.customer.Exception.ResourceNotFoundException;

import java.util.Optional;

@Service
public class
CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO saveCustomer(CustomerFormDTO body) {
        Optional<Customer> customerOptionalEmail = this.customerRepository.findByEmail(body.getEmail());
        Optional<Customer> customerOptionalCPF = this.customerRepository.findByCPF(body.getCPF());
        if (customerOptionalEmail.isPresent()) {
            throw new ResourceNotFoundException("Customer by Email: " + body.getEmail() + " already exists");
        }
        if (customerOptionalCPF.isPresent()) {
            throw new ResourceNotFoundException("Customer by CPF: " + body.getCPF() + " already exists");
        }
        Customer customer = modelMapper.map(body, Customer.class);
        Customer savedCustomer = this.customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO searchCustomer(Long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            return modelMapper.map(customer.get(), CustomerDTO.class);
        }
        throw new ResourceNotFoundException("Customer not found" + id);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerFormDTO body) {
        Optional<Customer> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            Customer updatedCustomer = modelMapper.map(body, Customer.class);
            updatedCustomer.setId(customer.get().getId());
            this.customerRepository.save(updatedCustomer);

            return modelMapper.map(updatedCustomer, CustomerDTO.class);
        }
        throw new ResourceNotFoundException("Customer not found" + id);
    }
}
