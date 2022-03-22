package shop.style.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.style.customer.Entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
