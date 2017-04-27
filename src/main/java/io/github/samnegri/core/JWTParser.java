package io.github.samnegri.core;

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
        Map<String,String> header = jwt.getHeader();
        header.put("alg", signer.getAlgorithm().getName());
        String header64encoded = wrap(Json.toJson(header));
        String payload64encoded = wrap(jwt.getPayload());

        return Optional.of(String.join(".", header64encoded, payload64encoded))
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .map(jwtSignature -> String.join(".", header64encoded, payload64encoded, jwtSignature))
            .get();
    }

    public JWT validate(String token) {
        String[] tokenSplitted = token.split(Pattern.quote("."));
        String data = tokenSplitted[0] + "." + tokenSplitted[1];
        String signed = tokenSplitted[2];

        HashMap<String, String> header = (HashMap<String, String>) Json.fromJson(unwrap(tokenSplitted[0]).get());
        String payload = unwrap(tokenSplitted[1]).get();
        JWT jwt = new JWT(header,payload,signed);

        Optional.of(data)
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .filter(signed::equals)
            .get();
        return jwt;

    }

    private String wrap(String payload) {
        return Optional.of(payload)
            .map(base64::getBytes)
            .map(base64::encodeURLBase64)
            .get();
    }

    private Optional<String> unwrap(String payload) {
        return Optional.of(payload)
            .map(base64::decodeURLBase64)
            .map(base64::parseString);
    }
}
