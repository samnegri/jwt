package io.github.samnegri.core;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class HMACSHA256Signer implements Signer {

    @Override
    public byte[] sign(byte[] toBeSigned, byte[] secret) throws Exception{
        Mac hmacSHA256 = Mac.getInstance(Algorithm.HMACSHA256.getCode());
        Key key = new SecretKeySpec(secret, Algorithm.HMACSHA256.getCode());
        hmacSHA256.init(key);
        return hmacSHA256.doFinal(toBeSigned);
    }

    public static Signer getInstance() {
        return new HMACSHA256Signer();
    }
}
