package com.xpay.notification.controller;

import com.xpay.notification.constant.Routes;
import com.xpay.notification.service.OTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.BASE_API + Routes.User.BASE_NOTIFICATION)
@RequiredArgsConstructor
@Slf4j
public class OTPController {

    private final OTPService otpService;

    @PostMapping(Routes.User.SEND_OTP)
    public boolean sendOtp(@RequestBody String email) {
        if (email == null || email.isEmpty()) {
            log.error("Email is empty or null");
            return false;  // Return false when the email is invalid
        }

        try {
            log.info("Received request to send OTP to email {}", email);
            otpService.sendOtpForVerification(email);
            log.info("OTP sent to email {}", email);
            return true;  // Return true when OTP is successfully sent
        } catch (Exception e) {
            log.error("Error sending OTP to email {}: {}", email, e.getMessage());
            return false;  // Return false in case of any exception
        }
    }
}
