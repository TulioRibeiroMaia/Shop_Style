package shop.checkout.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.checkout.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
