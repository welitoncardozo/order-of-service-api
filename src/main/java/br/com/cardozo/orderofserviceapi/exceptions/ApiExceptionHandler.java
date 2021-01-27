package br.com.cardozo.orderofserviceapi.exceptions;

import br.com.cardozo.orderofserviceapi.exceptions.dto.FieldExceptionHandler;
import br.com.cardozo.orderofserviceapi.exceptions.dto.MessageExceptionHandler;
import br.com.cardozo.orderofserviceapi.translates.TranslateMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldExceptionHandler> fieldsException = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(fieldError -> FieldExceptionHandler.builder()
                        .name(fieldError.getField())
                        .message(TranslateMessage.getMessage(fieldError))
                        .build())
                .collect(toList());

        final MessageExceptionHandler messageExceptionHandler = MessageExceptionHandler.builder()
                .statusCode(status.value())
                .title("Campos inválidos, faça o preenchimento correto e tente novamente.")
                .fields(fieldsException)
                .occurrenceDate(LocalDateTime.now())
                .build();

        return super.handleExceptionInternal(ex, messageExceptionHandler, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(final BusinessException ex, final WebRequest request) {
        final MessageExceptionHandler messageExceptionHandler = MessageExceptionHandler.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .title(ex.getMessage())
                .occurrenceDate(LocalDateTime.now())
                .build();

        return super.handleExceptionInternal(ex, messageExceptionHandler, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        final MessageExceptionHandler messageExceptionHandler = MessageExceptionHandler.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .title(ex.getMessage())
                .occurrenceDate(LocalDateTime.now())
                .build();

        return super.handleExceptionInternal(ex, messageExceptionHandler, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
