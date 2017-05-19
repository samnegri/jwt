package io.github.samnegri.core;

/**
 * Created by Samuel Negri Morais on 5/19/17.
 */
public class HMACSHA384Signer extends Signer {

    private HMACSHA384Signer(byte[] secret) {
        super(secret);
    }

    public static Signer getInstance(byte[] secret) {
        return new HMACSHA384Signer(secret);
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.HMACSHA384;
    }
}
