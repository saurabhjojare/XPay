package com.xpay.transactions.constants;

public class ApiEndpoints {
    private ApiEndpoints() {}

    public static final String BASE_API = "/api";

    public static final class Transactions {
        public static final String BASE_TRANSACTIONS = "/transactions";
        public static final String SEND = "/send";
        public static final String GET_TRANSACTION_HISTORY = "/history/{walletId}";
    }
}
