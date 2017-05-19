package io.github.samnegri.core;

import io.github.samnegri.exception.InvalidTokenException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Samuel Negri Morais on 5/19/17.
 */
public class JWTParserTest {

    @Rule
    public ExpectedException rule = ExpectedException.none();

    @Test
    public void invalidTokenTest() throws UnsupportedEncodingException {
        rule.expect(InvalidTokenException.class);
        String signedJson = "some.random.string";
        JWTParser jwtParser = JWTParser.newInstance(Algorithm.HMACSHA256, "secret".getBytes(), "UTF-8");
        jwtParser.validate(signedJson);
    }

    @Test
    public void invalidToken2Test() throws UnsupportedEncodingException {
        rule.expect(InvalidTokenException.class);
        String signedJson = "invalidtokenstring";
        JWTParser jwtParser = JWTParser.newInstance(Algorithm.HMACSHA256, "secret".getBytes(), "UTF-8");
        jwtParser.validate(signedJson);
    }
}
