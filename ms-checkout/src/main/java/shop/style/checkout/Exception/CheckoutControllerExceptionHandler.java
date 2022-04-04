package shop.style.checkout.Exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import shop.style.checkout.Exception.DTO.FieldErrorDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class CheckoutControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_EXTENDED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(PaymentNotActiveException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage paymentNotActiveException(PaymentNotActiveException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage paymentNotFoundException(PaymentNotFoundException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
    }

    @ExceptionHandler(ProductNotActiveException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage productNotActiveException(ProductNotActiveException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
    }

    @ExceptionHandler(UserNotActiveException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userNotActiveException(UserNotActiveException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public FieldErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<FieldErrorDTO> dto = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            String field = fieldError.getField();
            String error = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            dto.add(new FieldErrorDTO(field, error));
        });

        dto.sort(Comparator.comparing(FieldErrorDTO::getField));

        return new FieldErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                dto,
                request.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getCause().getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage httpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getRootCause().getLocalizedMessage(),
                request.getDescription(false));
    }
}
