package io.github.samnegri.util;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class Base64 {

    private final String encoding;

    private Base64(String encoding){
        this.encoding = encoding;
    }

    public static Base64 newInstance(String encoding) {
        return new Base64(encoding);
    }

    public String encodeURLBase64(byte[] data) {
        return Optional.of(data)
            .map(DatatypeConverter::printBase64Binary)
            .map(base64 -> base64.replace('+', '-'))
            .map(base64 -> base64.replace('/', '_'))
            .get();
    }

    public byte[] decodeURLBase64(String data) {
        return Optional.of(data)
            .map(base64 -> base64.replace('/', '_'))
            .map(base64 -> base64.replace('+', '-'))
            .map(DatatypeConverter::parseBase64Binary)
            .get();
    }

    public byte[] getBytes(String json) {
        try {
            return json.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String parseString(byte[] bytes) {
        try {
            return new String(bytes,encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String wrap(String payload) {
        return Optional.of(payload)
            .map(this::getBytes)
            .map(this::encodeURLBase64)
            .get();
    }

    public String unwrap(String payload) {
        return Optional.of(payload)
            .map(this::decodeURLBase64)
            .map(this::parseString).get();
    }
}
