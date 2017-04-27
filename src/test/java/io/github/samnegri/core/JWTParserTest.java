package io.github.samnegri.core;

import io.github.samnegri.util.Json;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JWTParserTest {

    @Test
    public void validateTokenTest() throws UnsupportedEncodingException {
        TestPojo foo = new TestPojo();
        foo.setArg("test");
        JWT jwt = new JWT(Json.toJson(foo));
        String charsetName = "UTF-8";
        JWTParser jwtParser = JWTParser.newInstance(Algorithm.HMACSHA256, "secret".getBytes(charsetName), charsetName);
        String signedJson = jwtParser.create(jwt);
        System.out.println(signedJson);

        assertNotNull(signedJson);

        JWT validate = jwtParser.validate(signedJson);

        assertThat(jwt.getHeader().size(), is(validate.getHeader().size()));
        jwt.getHeader().keySet().forEach(key -> assertEquals(jwt.getHeader().get(key), validate.getHeader().get(key)));
        assertThat(jwt.getPayload(), is(validate.getPayload()));
    }

}