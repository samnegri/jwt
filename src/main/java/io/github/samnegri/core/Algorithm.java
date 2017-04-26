package io.github.samnegri.core;

public enum Algorithm {
    HMACSHA256("HmacSHA256", HMACSHA256Signer.getInstance());

    private final String code;
    private final Signer signer;

    Algorithm(String code, Signer signer) {
        this.code = code;
        this.signer = signer;
    }

    public String getCode() {
        return code;
    }

    public Signer getSigner() {
        return signer;
    }

}
