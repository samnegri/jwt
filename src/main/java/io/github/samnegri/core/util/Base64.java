package io.github.samnegri.core.util;

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

    public byte[] getBytes(String json) {
        try {
            return json.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
