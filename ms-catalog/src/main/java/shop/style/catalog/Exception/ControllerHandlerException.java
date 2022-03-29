package shop.style.catalog.Exception;

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
import shop.style.catalog.Exception.DTO.FieldErrorDTO;

import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class ControllerHandlerException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage noSuchElementException(NoSuchElementException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getLocalizedMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage invalidElementsException(ResourceNotFoundException exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(ResourceIsNotActive.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceIsNotActive(ResourceIsNotActive exception, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public FieldErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
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
    public ErrorMessage illegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage constraintViolationException(ConstraintViolationException exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getCause().getLocalizedMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage httpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getRootCause().getLocalizedMessage(),
                request.getDescription(false));
    }
}
