package io.github.samnegri.core;

public class JWTHeader {

    private String iss;
    private String sub;
    private String aud;
    private String exp;
    private String nbf;
    private String iat;
    private String jti;
    private String typ;
    private String cty;

    public String getIss() {
        return iss;
    }

    public String getSub() {
        return sub;
    }

    public String getAud() {
        return aud;
    }

    public String getExp() {
        return exp;
    }

    public String getNbf() {
        return nbf;
    }

    public String getIat() {
        return iat;
    }

    public String getJti() {
        return jti;
    }

    public String getTyp() {
        return typ;
    }

    public String getCty() {
        return cty;
    }

    public Builder builder(){
        return new Builder();
    }

    class Builder {
        private String iss;
        private String sub;
        private String aud;
        private String exp;
        private String nbf;
        private String iat;
        private String jti;
        private String typ;
        private String cty;

        Builder() {
            this.typ = "JWT";
        }

        public Builder iss(String iss) {
            this.iss = iss;
            return this;
        }

        public Builder sub(String sub) {
            this.sub = sub;
            return this;
        }

        public Builder aud(String aud) {
            this.aud = aud;
            return this;
        }

        public Builder exp(String exp) {
            this.exp = exp;
            return this;
        }

        public Builder nbf(String nbf) {
            this.nbf = nbf;
            return this;
        }

        public Builder iat(String iat) {
            this.iat = iat;
            return this;
        }

        public Builder jti(String jti) {
            this.jti = jti;
            return this;
        }

        public Builder typ(String typ) {
            this.typ = typ;
            return this;
        }

        public Builder cty(String cty) {
            this.cty = cty;
            return this;
        }
    }
}
