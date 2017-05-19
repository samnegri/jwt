package io.github.samnegri.core;

public enum Algorithm {
    HMACSHA256("HSHA256", "HmacSHA256"),
    HMACSHA384("HSHA384", "HmacSHA384");

    private final String name;
    private final String code;

    Algorithm(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
