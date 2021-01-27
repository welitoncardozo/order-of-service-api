package br.com.cardozo.orderofserviceapi.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(final String message) {
        super(message);
    }
}
