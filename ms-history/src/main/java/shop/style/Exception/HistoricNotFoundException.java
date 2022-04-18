package shop.style.Exception;

public class HistoricNotFoundException extends RuntimeException {
    public HistoricNotFoundException(Long userId) {
        super(String.valueOf(userId));
    }
}
