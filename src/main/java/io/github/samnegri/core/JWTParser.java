package io.github.samnegri.core;

import io.github.samnegri.exception.InvalidTokenException;
import io.github.samnegri.util.Base64;
import io.github.samnegri.util.Json;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class JWTParser {
    private final Base64 base64;
    private final Signer signer;

    private JWTParser(Algorithm algorithm, byte[] secret, String encoding) {
        this.signer = JWTSignerFactory.getSignerFor(algorithm, secret);
        base64 = Base64.newInstance(encoding);
    }

    public static JWTParser newInstance(Algorithm algorithm, byte[] secret, String encoding) {
        return new JWTParser(algorithm, secret, encoding);
    }

    public String create(JWT jwt) {
        Map<String, String> header = jwt.getHeader();
        header.put("alg", signer.getAlgorithm().getName());
        String header64encoded = base64.wrap(Json.toJson(header));
        String payload64encoded = base64.wrap(jwt.getPayload());

        String signature = sign(header64encoded, payload64encoded);
        return String.join(".", header64encoded, payload64encoded, signature);
    }

    public JWT validate(String token) {
        String[] tokenSplitted = token.split(Pattern.quote("."));
        if (tokenSplitted.length != 3) {
            throw new InvalidTokenException();
        }
        return validate(tokenSplitted[0], tokenSplitted[1], tokenSplitted[2]);
    }

    private String sign(String header64encoded, String payload64encoded) {
        return Optional.of(String.join(".", header64encoded, payload64encoded))
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64).get();
    }

    private JWT validate(String headerBase64, String payload64, String tokenSignature) {
        checkSignature(headerBase64, payload64, tokenSignature);

        Map header = Json.fromJson(base64.unwrap(headerBase64), HashMap.class);
        String payload = base64.unwrap(payload64);

        return new JWT(header, payload, tokenSignature);

    }

    private void checkSignature(String headerBase64, String payload64, String tokenSignature) {
        String signature = sign(headerBase64, payload64);
        if (!signature.equals(tokenSignature)) {
            throw new InvalidTokenException();
        }
    }
}
