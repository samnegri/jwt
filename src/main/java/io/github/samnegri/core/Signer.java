package io.github.samnegri.core;

public abstract class Signer {

    private final byte[] secret;

    public Signer(byte[] secret) {
        this.secret = secret;
    }

    byte[] sign(byte[] toBeSigned) {
        return getAlgorithm().encript(secret, toBeSigned);
    }

    public abstract Algorithm getAlgorithm();
}
