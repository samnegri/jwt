package io.github.samnegri.core;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class HMACSHA256Signer implements Signer {

    @Override
    public byte[] sign(byte[] toBeSigned, byte[] secret) {
        try {
            Mac hmacSHA256 = Mac.getInstance(Algorithm.HMACSHA256.getCode());
            Key key = new SecretKeySpec(secret, Algorithm.HMACSHA256.getCode());
            hmacSHA256.init(key);
            return hmacSHA256.doFinal(toBeSigned);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static Signer getInstance() {
        return new HMACSHA256Signer();
    }
}
