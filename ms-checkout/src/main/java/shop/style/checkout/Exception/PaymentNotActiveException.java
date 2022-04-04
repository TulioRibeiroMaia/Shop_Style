package shop.style.checkout.Exception;

public class PaymentNotActiveException extends RuntimeException {
    public PaymentNotActiveException( Long payment_id) {

        super(String.valueOf(payment_id));
    }
}
