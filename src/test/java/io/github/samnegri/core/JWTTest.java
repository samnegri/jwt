package io.github.samnegri.core;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JWTTest {

    @Test
    public void createTest() throws UnsupportedEncodingException {
        String json = "{\"foo\":\"bar\"}";
        String charsetName = "UTF-8";
        JWT jwt = JWT.newInstance(Algorithm.HMACSHA256, "secret".getBytes(charsetName), charsetName);
        String signedJson = jwt.create(json);
        System.out.println(signedJson);
        assertNotNull(signedJson);
        assertTrue(jwt.validate(json,"PzqzmGtlarsXrz6xRD7WwI74__n-qDkVkJ0bQhrsib4="));
    }

}