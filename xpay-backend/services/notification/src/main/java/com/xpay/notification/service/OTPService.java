package com.xpay.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class OTPService {
    private static final String OTP_KEY_PREFIX = "otp:";

    private static final long OTP_TTL_SECONDS = 300;

    private final RedisService redisService;
    private final UserEmailService userEmailService;

    public void sendOtpForVerification(String email) {
        String otp = generateOtp();
        String redisKey = OTP_KEY_PREFIX + email;

        redisService.setValueWithTTL(redisKey,otp, OTP_TTL_SECONDS);

        userEmailService.sendOTPEmail(email, otp);
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otpInt = 100000 + random.nextInt(900000);
        return String.valueOf(otpInt);
    }
}
