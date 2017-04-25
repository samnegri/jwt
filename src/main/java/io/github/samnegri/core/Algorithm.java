package io.github.samnegri.core;

public enum Algorithm {
    HMACSHA256("HmacSHA256", HMACSHA256Signer.getInstance(), "{ \"alg\":\"HS256\", \"typ\":\"JWT\"}");

    private final String code;
    private final Signer signer;
    private final String header;

    Algorithm(String code, Signer signer, String header) {
        this.code = code;
        this.signer = signer;
        this.header = header;
    }

    public String getCode() {
        return code;
    }

    public Signer getSigner() {
        return signer;
    }

    public String getHeader() {
        return header;
    }
}
