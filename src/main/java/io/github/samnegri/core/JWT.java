package io.github.samnegri.core;

import io.github.samnegri.core.util.Base64;
import io.github.samnegri.core.util.Json;

import java.util.Optional;

public class JWT {
    private final Algorithm algorithm;
    private final String payload;
    private final Base64 base64;
    private final Signer signer;

    private JWT(Algorithm algorithm, byte[] secret, String payload, String encoding) {
        this.algorithm = algorithm;
        this.payload = payload;
        this.signer = JWTSignerFactory.getSignerFor(algorithm, secret);
        base64 = Base64.newInstance(encoding);
    }

    public static JWT newInstance(Algorithm algorithm, byte[] secret, String payload, String encoding) {
        return new JWT(algorithm, secret, payload, encoding);
    }

    public String create() {
        String header64encoded = createJWTHeader();
        String payload64encoded = createPayload();

        return Optional.of(String.join(".", header64encoded, payload64encoded))
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .map(jwtSignature -> String.join(".", header64encoded, payload64encoded, jwtSignature))
            .get();
    }

    private String createPayload() {
        return Optional.of(payload)
            .map(base64::getBytes)
            .map(base64::encodeURLBase64)
            .get();
    }

    private String createJWTHeader() {
        JWTHeader header = JWTHeader.builder()
            .alg(algorithm.getName())
            .build();
        return Optional.of(header)
            .map(Json::toJson)
            .map(base64::getBytes)
            .map(base64::encodeURLBase64)
            .get();
    }

    public boolean validate(String data, String signed) {
        return Optional.of(data)
            .map(base64::getBytes)
            .map(signer::sign)
            .map(base64::encodeURLBase64)
            .get().equals(signed);
    }
}
