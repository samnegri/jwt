package io.github.samnegri.core;

import io.github.samnegri.util.Base64;
import io.github.samnegri.util.Json;

import java.util.Optional;

public class JWT {
    private final Algorithm algorithm;
    private final Base64 base64;
    private final Signer signer;

    private JWT(Algorithm algorithm, byte[] secret, String encoding) {
        this.algorithm = algorithm;
        this.signer = JWTSignerFactory.getSignerFor(algorithm, secret);
        base64 = Base64.newInstance(encoding);
    }

    public static JWT newInstance(Algorithm algorithm, byte[] secret, String encoding) {
        return new JWT(algorithm, secret, encoding);
    }

    public String create(String payload) {
        JWTHeader header = JWTHeader.builder()
            .alg(algorithm.getName())
            .build();
        String header64encoded = prepareHeader(header);
        String payload64encoded = preparePayload(payload);

        return Optional.of(String.join(".", header64encoded, payload64encoded))
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .map(jwtSignature -> String.join(".", header64encoded, payload64encoded, jwtSignature))
            .get();
    }

    public boolean validate(String data, String signed) {
        return Optional.of(data)
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .map(signed::equals)
            .get();
    }

    private String preparePayload(String payload) {
        return Optional.of(payload)
            .map(base64::getBytes)
            .map(base64::encodeURLBase64)
            .get();
    }

    private String prepareHeader(JWTHeader header) {
        return Optional.of(header)
            .map(Json::toJson)
            .map(base64::getBytes)
            .map(base64::encodeURLBase64)
            .get();
    }
}
