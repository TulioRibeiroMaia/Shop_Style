package shop.checkout.Exception;

public class UserNotActiveException extends RuntimeException {
    public UserNotActiveException( Long user_id) {

        super(String.valueOf(user_id));
    }
}
