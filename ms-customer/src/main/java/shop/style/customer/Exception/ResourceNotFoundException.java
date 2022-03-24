package shop.style.customer.Exception;

public class ResourceNotFoundException extends RuntimeException {

    static final String DEFAULT_MSG = "Recurso não encontrado: ";

    public ResourceNotFoundException(Object msg) {
        super(DEFAULT_MSG + msg);
    }
}
