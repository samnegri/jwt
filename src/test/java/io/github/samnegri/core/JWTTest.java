package io.github.samnegri.core;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JWTTest {

    @Test
    public void createTest() throws UnsupportedEncodingException {
        String json = "{\"foo\":\"bar\"}";
        JWT jwt = JWT.newInstance(Algorithm.HMACSHA256, "secret".getBytes(JWT.ENCODING), json, JWT.ENCODING);
        String signedJson = jwt.create();
        System.out.println(signedJson);
        assertNotNull(signedJson);
        assertTrue(jwt.validate(json,"PzqzmGtlarsXrz6xRD7WwI74__n-qDkVkJ0bQhrsib4="));
    }

}