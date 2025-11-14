package com.xpay.wallet.constants;

public class ApiEndpoints {
    private ApiEndpoints() {}

    public static final String BASE_API = "/api";

    public static final class Wallet {
        public static final String BASE_WALLET = "/wallet";
        public static final String GET_BALANCE_BY_ID = "/balance/{userId}";
        public static final String DEPOSIT = "/deposit";
    }
}
