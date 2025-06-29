package com.xpay.userservice.constants;

public class ApiEndpoints {
    private ApiEndpoints() {}

    public static final String BASE_API = "/api";

    public static final class Users {
        public static final String BASE_USERS = "/users";
        public static final String GET_BY_ID = "/{id}";
    }
}
