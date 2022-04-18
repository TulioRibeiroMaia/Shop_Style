package shop.checkout.Exception;

public class ProductNotActiveException extends RuntimeException {
    public ProductNotActiveException(String id) {

        super(id);
    }
}
