package com.androidwatcher.exception;

public class CryptException extends RuntimeException {

    static final long serialVersionUID = -46235243l;

    public CryptException() {
        super();
    }

    public CryptException(String message) {
        super(message);
    }

    public CryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptException(Throwable cause) {
        super(cause);
    }
}
