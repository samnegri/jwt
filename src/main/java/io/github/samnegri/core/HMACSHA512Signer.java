package io.github.samnegri.core;

import io.github.samnegri.exception.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Samuel Negri Morais on 5/19/17.
 */
public class HMACSHA512Signer implements Signer {
    private final byte[] secret;

    public HMACSHA512Signer(byte[] secret) {
        this.secret = secret;
    }

    public static Signer getInstance(byte[] secret) {
        return new HMACSHA512Signer(secret);
    }

    @Override
    public byte[] sign(byte[] toBeSigned) {
        try {
            Mac hmacSHA384 = Mac.getInstance(Algorithm.HMACSHA512.getCode());
            Key key = new SecretKeySpec(secret, Algorithm.HMACSHA512.getCode());
            hmacSHA384.init(key);
            return hmacSHA384.doFinal(toBeSigned);
        } catch (NoSuchAlgorithmException e) {
            throw new SignatureException("Invalid algorithm for signing: ", e);
        } catch (InvalidKeyException e) {
            throw new SignatureException("Invalid key for signing: ", e);
        }
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.HMACSHA512;
    }
}
