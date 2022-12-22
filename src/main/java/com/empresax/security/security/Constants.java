package com.empresax.security.security;

public class Constants {

    public static final String ISSUER = "@efxngg";
    public static final String ENCODER_ID = "x";
    public static final String API_URL_PREFIX = "/api/v1/**";
    public static final long EXPIRATION_TIME = 3600000;
    public static final String ROLE_CLAIM = "roles";
    public static final String AUTHORITY_PREFIX = "ROLE_";
    public static final String SIGN_UP = "/api/v1/auth/sign-up";
    public static final String SIGN_IN = "/api/v1/auth/sign-in";
    public static final String TOKEN_REFRESH = "/api/v1/auth/refresh";
    public static final String SIGN_OUT = "/api/v1/auth/sign-out";
    public static final String[] WITHLIST = {"/v3/api-docs/**", "/swagger-ui/**"};



}
