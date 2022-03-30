package shop.style.checkout.Exception;

public class ResourceNotFoundException extends RuntimeException {
    static final String DEFAULT_MSG = "Recurso não encontrado: ";

    public ResourceNotFoundException(String msg) {
        super(DEFAULT_MSG + msg);
    }
}
