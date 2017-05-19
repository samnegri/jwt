package io.github.samnegri.core;

/**
 * Created by Samuel Negri Morais on 5/19/17.
 */
public class HMACSHA256Signer extends Signer {

    private HMACSHA256Signer(byte[] secret) {
        super(secret);
    }

    public static Signer getInstance(byte[] secret) {
        return new HMACSHA256Signer(secret);
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.HMACSHA256;
    }
}
