package io.github.samnegri.core;

public interface Signer {

    byte[] sign(byte[] toBeSigned);

}
