package io.github.samnegri.core.util;

import javax.xml.bind.DatatypeConverter;
import java.util.Optional;

public class Base64 {

    public static String encodeURLBase64(byte[] data) {
        return Optional.of(data)
            .map(DatatypeConverter::printBase64Binary)
            .map(base64 -> base64.replace('+', '-'))
            .map(base64 -> base64.replace('/', '_'))
            .get();
    }
}
