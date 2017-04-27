package io.github.samnegri.core;

import java.util.HashMap;
import java.util.Map;

public class JWT {
    private Map<String, String> header;
    private String payload;
    private String signature;

    public JWT(String payload) {
        this.payload = payload;
        this.header = new HashMap<>();
    }

    JWT(Map<String,String> header, String payload, String signature) {
        this.payload = payload;
        this.header = header;
        this.signature = signature;
    }

    public Map<String,String> getHeader() {
        return header;
    }

    public String getPayload() {
        return payload;
    }

    public String getSignature() {
        return signature;
    }
}
