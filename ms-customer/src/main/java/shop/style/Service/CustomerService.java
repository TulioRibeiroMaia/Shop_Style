package shop.style.Service;

import shop.style.DTO.CustomerDTO;
import shop.style.DTO.FormDTO.CustomerFormDTO;

public interface CustomerService {


    CustomerDTO saveCustomer(CustomerFormDTO body);

    CustomerDTO findCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerFormDTO body);
}
