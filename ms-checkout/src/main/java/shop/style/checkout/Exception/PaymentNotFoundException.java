package shop.style.checkout.Exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException( Long payment_id) {

        super(String.valueOf(payment_id));
    }
}
