package net.playground.casclient.rest;

public class RestApplicationException extends RuntimeException {

    public RestApplicationException(String message) {
        super(message);
    }

    public RestApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}