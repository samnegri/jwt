package io.github.samnegri.core;

import io.github.samnegri.core.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class JWT {
    public static String ENCODING = "UTF-8";
    private final Algorithm algorithm;
    private final byte[] secret;
    private final String payload;
    private final String encoding;

    private JWT(Algorithm algorithm, byte[] secret, String payload, String encoding) {
        this.algorithm = algorithm;
        this.secret = secret;
        this.payload = payload;
        this.encoding = encoding;
    }

    public static JWT newInstance(Algorithm algorithm, byte[] secret, String payload, String encoding) {
        return new JWT(algorithm, secret, payload, encoding);
    }

    public String create() {
        try {
            byte[] header = "{ \"alg\":\"HS256\", \"typ\":\"JWT\"}".getBytes(encoding);
            String header64encoded = Base64.encodeURLBase64(header);
            String payload64encoded = Base64.encodeURLBase64(payload.getBytes(encoding));

            Signer signer = algorithm.getSigner();
            return Optional.of(String.join(".", header64encoded, payload64encoded).getBytes(encoding))
                .map(data -> signer.sign(data, secret))
                .map(Base64::encodeURLBase64)
                .map(jwtSignature -> String.join(".", header64encoded, payload64encoded, jwtSignature))
                .get();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(String data, String signed) {
        try {
            Signer signer = algorithm.getSigner();
            return Optional.of(data.getBytes(encoding))
                .map(bytes -> signer.sign(bytes, secret))
                .map(Base64::encodeURLBase64)
                .get().equals(signed);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
