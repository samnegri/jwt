package io.github.samnegri.exception;

public class SignatureException extends RuntimeException {
    public SignatureException(String message, Exception e) {
        super(message, e);
    }

}
