package io.github.samnegri.core;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JWTTest {

    @Test
    public void createTest() throws Exception {
        String json = "{\"foo\":\"bar\"}";
        JWT jwt = JWT.newInstance()
            .algorithm(Algorithm.HMACSHA256)
            .secret("secret")
            .payload(json);
        String signedJson = jwt.create();
        System.out.println(signedJson);
        assertNotNull(signedJson);
        assertTrue(jwt.validate(json,"PzqzmGtlarsXrz6xRD7WwI74//n+qDkVkJ0bQhrsib4="));
    }

}