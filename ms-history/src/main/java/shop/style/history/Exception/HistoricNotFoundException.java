package shop.style.history.Exception;

public class HistoricNotFoundException extends RuntimeException {
    public HistoricNotFoundException(Long userId) {
        super(String.valueOf(userId));
    }
}
