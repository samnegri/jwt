package io.github.samnegri.core;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

public class JWT {
    private String algorithm;
    private String secret;

    public static JWT newInstance() {
        return new JWT();
    }

    public JWT algorithm(String algorithm){
        this.algorithm = algorithm;
        return this;
    }

    public String create(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String alg = "HmacSHA256";
        Mac hmacSHA256 = Mac.getInstance(alg);
        Key key = new SecretKeySpec(secret.getBytes("UTF-8"),alg);
        hmacSHA256.init(key);
        byte[] bytes = hmacSHA256.doFinal(s.getBytes());
        String ret = DatatypeConverter.printBase64Binary(bytes);
        return ret;
    }

    public JWT secret(String secret) {
        this.secret = secret;
        return this;
    }
}
