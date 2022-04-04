package shop.style.checkout.Exception;

public class ProductNotActiveException extends RuntimeException {
    public ProductNotActiveException(String id) {

        super(id);
    }
}
