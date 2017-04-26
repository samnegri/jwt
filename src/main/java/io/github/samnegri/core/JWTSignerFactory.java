package io.github.samnegri.core;

public class JWTSignerFactory {

    public static Signer getSignerFor(Algorithm algorithm, byte[] secret) {
        return HMACSHA256Signer.getInstance(secret);
    }

}
