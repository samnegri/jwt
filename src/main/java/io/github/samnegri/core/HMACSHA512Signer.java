package io.github.samnegri.core;

/**
 * Created by Samuel Negri Morais on 5/19/17.
 */
public class HMACSHA512Signer extends Signer {

    private HMACSHA512Signer(byte[] secret) {
        super(secret);
    }

    public static Signer getInstance(byte[] secret) {
        return new HMACSHA512Signer(secret);
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.HMACSHA512;
    }
}
