package com.xpay.common.constants;

public final class ApiEndpoints {

    private ApiEndpoints() {}

    public static final String BASE_API = "/api/v1";

    public static final class Users {
        public static final String BASE = "/users";
        public static final String CREATE = "/create";
        public static final String GET_BY_ID = "/{id}";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }
}
