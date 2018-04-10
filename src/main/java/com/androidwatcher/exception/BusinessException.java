package com.androidwatcher.exception;

public class BusinessException extends RuntimeException {

    static final long serialVersionUID = -4643524325345l;

    private int status=-1;

    public int getStatus() {
        return status;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(int status) {
        super();
        this.status=status;
    }

    public BusinessException(int status,String message) {
        super(message);
        this.status=status;
    }

    public BusinessException(int status,String message, Throwable cause) {
        super(message, cause);
        this.status=status;
    }

    public BusinessException(int status,Throwable cause) {
        super(cause);
        this.status=status;
    }

}
