package com.xpay.notifications.service;

import com.xpay.notifications.dto.event.UserCreatedEventDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public void sendWelcomeEmail(UserCreatedEventDTO userCreatedEventDTO) {
        Context context = new Context();
        context.setVariable("firstName", userCreatedEventDTO.getFirstName());
        context.setVariable("lastName", userCreatedEventDTO.getLastName());

        String htmlContent = templateEngine.process("welcome-user-email", context);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

            mimeMessageHelper.setTo(userCreatedEventDTO.getEmail());
            mimeMessageHelper.setSubject("Welcome to XPay!");
            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            log.info("Welcome email sent to {}", userCreatedEventDTO.getEmail());
        } catch (Exception e) {
            log.error("Failed to send welcome email to {}", userCreatedEventDTO.getEmail(), e);
        }
    }
}
