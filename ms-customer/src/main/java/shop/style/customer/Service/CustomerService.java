package shop.style.customer.Service;

import shop.style.customer.DTO.CustomerDTO;
import shop.style.customer.DTO.FormDTO.CustomerFormDTO;

public interface CustomerService {


    CustomerDTO saveCustomer(CustomerFormDTO body);

    CustomerDTO searchCustomer(Long id);

    CustomerDTO updateCustomer(Long id, CustomerFormDTO body);
}
