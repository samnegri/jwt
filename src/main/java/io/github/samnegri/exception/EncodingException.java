package io.github.samnegri.exception;

public class EncodingException extends RuntimeException {

    public EncodingException(String message, Exception e) {
        super(message, e);
    }
}
