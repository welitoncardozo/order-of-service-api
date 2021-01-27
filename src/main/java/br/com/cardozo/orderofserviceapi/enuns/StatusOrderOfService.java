package br.com.cardozo.orderofserviceapi.enuns;

public enum StatusOrderOfService {
    OPEN,
    FINISHED,
    CANCELED;

    public boolean isOpen() {
        return OPEN.equals(this);
    }

    public boolean isNotOpen() {
        return !isOpen();
    }
}
