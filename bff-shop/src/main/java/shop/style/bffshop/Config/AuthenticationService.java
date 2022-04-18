package shop.style.bffshop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.style.bffshop.Entity.Customer;
import shop.style.bffshop.Repository.CustomerRepository;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(username);
        if (customer.isPresent()) {
            return (UserDetails) customer.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos!");
    }
}
