package com.referafriendspringapp.handler;

import com.referafriendspringapp.dto.ReferralDTO;
import com.referafriendspringapp.dto.BaseResponseDTO;
import com.referafriendspringapp.exception.CustomException;
import com.referafriendspringapp.helpers.EmailHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.apache.commons.validator.routines.EmailValidator;

import static com.referafriendspringapp.constants.GlobalConstants.ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL;
import static com.referafriendspringapp.constants.GlobalConstants.SUCCESS_MSG_REFERRAL_EMAIL_SENT;
import static com.referafriendspringapp.constants.GlobalConstants.ERROR_MSG_INVALID_EMAIL;

@Component("v1.ReferralHandler")
public class ReferralHandler {
    @Autowired
    private JavaMailSender emailSender;

    public BaseResponseDTO sendReferralByEmail(ReferralDTO referralDTO) {
        if(!EmailValidator.getInstance().isValid(referralDTO.getEmail())) {
            throw new CustomException(ERROR_MSG_INVALID_EMAIL,
                    HttpStatus.BAD_REQUEST);
        }

        try {
            emailSender
                    .send(EmailHelpers.constructReferralEmail(emailSender, referralDTO.getEmail()));
            BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
            baseResponseDTO.setMessage(String.format(SUCCESS_MSG_REFERRAL_EMAIL_SENT, referralDTO.getEmail()));
            return baseResponseDTO;
        } catch(Exception e) {
            throw new CustomException(ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL,
                    HttpStatus.BAD_REQUEST);
        }
    }
}
