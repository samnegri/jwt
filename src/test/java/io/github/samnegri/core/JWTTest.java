package io.github.samnegri.core;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;

public class JWTTest {

    @Test
    public void createTest() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        JWT jwt = JWT.newInstance()
            .algorithm("")
            .secret("secret");
        String s = jwt.create("{\"foo\":\"bar\"}");
        System.out.println(s);
        assertNotNull(s);
    }

}