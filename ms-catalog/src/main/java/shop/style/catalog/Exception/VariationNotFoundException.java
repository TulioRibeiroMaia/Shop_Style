package shop.style.catalog.Exception;

public class VariationNotFoundException extends RuntimeException {
    static final String DEFAULT_MSG = "Recurso não encontrado: ";

    public VariationNotFoundException(String msg) {
        super(DEFAULT_MSG + msg);
    }
}
