package shop.style.checkout.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.style.checkout.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
