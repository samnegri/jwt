package io.github.samnegri.core;

public class JWTSignerFactory {

    public static Signer getSignerFor(Algorithm algorithm, byte[] secret) {
        switch (algorithm) {
            case HMACSHA256:
                return HMACSHA256Signer.getInstance(secret);
            case HMACSHA384:
                return HMACSHA384Signer.getInstance(secret);
            case HMACSHA512:
                return HMACSHA512Signer.getInstance(secret);
        }
        throw new RuntimeException("");
    }

}
