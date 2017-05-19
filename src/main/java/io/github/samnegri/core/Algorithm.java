package io.github.samnegri.core;

import io.github.samnegri.exception.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public enum Algorithm {
    HMACSHA256("HSHA256", "HmacSHA256"),
    HMACSHA384("HSHA384", "HmacSHA384"),
    HMACSHA512("HSHA512", "HmacSHA512" );

    private final String name;
    private final String code;

    Algorithm(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    byte[] encript(byte[] secret, byte[] toBeSigned) {
        try {
            Mac hmacSHA384 = Mac.getInstance(getCode());
            Key key = new SecretKeySpec(secret, getCode());
            hmacSHA384.init(key);
            return hmacSHA384.doFinal(toBeSigned);
        } catch (NoSuchAlgorithmException e) {
            throw new SignatureException("Invalid algorithm for signing: ", e);
        } catch (InvalidKeyException e) {
            throw new SignatureException("Invalid key for signing: ", e);
        }
    }
}
