package io.github.samnegri.core;

import javax.xml.bind.DatatypeConverter;
import java.sql.Blob;

public class JWT {
    public static String ENCODING = "UTF-8";
    private Algorithm algorithm;
    private String secret;
    private String payload;

    public static JWT newInstance() {
        return new JWT();
    }

    public JWT algorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String create() throws Exception {
        String header = algorithm.getHeader();
        String payload64 = DatatypeConverter.printBase64Binary(payload.getBytes(ENCODING));
        String header64 = DatatypeConverter.printBase64Binary(header.getBytes(ENCODING));
        Signer signer = algorithm.getSigner();
        byte[] toBeSigned = (payload64 + "." + header64).getBytes(ENCODING);
        byte[] signed = signer.sign(toBeSigned, secret.getBytes(ENCODING));
        String jwtSignature = DatatypeConverter.printBase64Binary(signed);
        return String.join(".", header64, payload64, jwtSignature);
    }

    public boolean validate(String s, String signed) throws Exception {
        Signer signer = algorithm.getSigner();
        byte[] toBeValidated = s.getBytes("UTF-8");
        toBeValidated = signer.sign(toBeValidated, secret.getBytes("UTF-8"));
        return DatatypeConverter.printBase64Binary(toBeValidated).equals(signed);
    }

    public JWT secret(String secret) {
        this.secret = secret;
        return this;
    }

    public JWT payload(String payload) {
        this.payload = payload;
        return this;
    }
}
