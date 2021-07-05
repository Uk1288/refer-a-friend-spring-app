package com.referafriendspringapp.helpers;

import static com.referafriendspringapp.constants.GlobalConstants.CLASSRT_SUPPORT_EMAIL_ADDRESS;
import static com.referafriendspringapp.constants.GlobalConstants.ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL;

import com.referafriendspringapp.exception.CustomException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailHelpers {
    public static MimeMessage constructReferralEmail(JavaMailSender emailSender, String email) {
//        there are easier ways to do this using templates libraries, but for the purpose of the test, I write these as strings.
        String subject = "You have been invited to ClassRT!";
        String firstLine = "<img src=\"cid:classRTLogo\" alt=\"Refer a friend Logo\"><p style=\"color:#696E80;\">Hi there! </p>";
        String secLine = "<p style=\"color:#696E80;\">Congrats! You have been referred to ClassRT by a friend, sign up and receive your 20% discount.</p>";
        String lastLine = "<p style=\"color:#696E80;\">Regards,</p> <p style=\"font-style:italic;color:grey;\">ClassRT Management.</p>";
        return constructEmail(emailSender, subject,
                firstLine + secLine + lastLine, email);
    }

    public static MimeMessage constructEmail(JavaMailSender emailSender, String subject, String body, String email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject(subject);
            MimeMessageHelper msgHelper = new MimeMessageHelper(message, true);
            msgHelper.setText(body, true);
            msgHelper.setTo(email);
            msgHelper.setFrom(CLASSRT_SUPPORT_EMAIL_ADDRESS);
            msgHelper.addInline("classRTLogo", new ClassPathResource("images/main_logo.png"));
            return message;
        } catch(MessagingException ex) {
            throw new CustomException(ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL,
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
