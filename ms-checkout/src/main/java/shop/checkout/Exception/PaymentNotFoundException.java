package shop.checkout.Exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException( Long payment_id) {

        super(String.valueOf(payment_id));
    }
}
