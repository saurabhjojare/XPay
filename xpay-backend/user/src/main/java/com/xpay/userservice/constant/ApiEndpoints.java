package com.xpay.userservice.constant;

public class ApiEndpoints {
    private ApiEndpoints() {}

    public static final String BASE_API = "/api";

    public static final class User {
        public static final String BASE_USERS = "/user";
        public static final String GET_BY_ID = "/{userId}";
        public static final String LOGIN = "/login";
    }
}
