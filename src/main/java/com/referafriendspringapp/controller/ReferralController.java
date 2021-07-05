package com.referafriendspringapp.controller;

import com.referafriendspringapp.dto.BaseResponseDTO;
import com.referafriendspringapp.dto.ReferralDTO;
import com.referafriendspringapp.handler.ReferralHandler;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.referafriendspringapp.constants.GlobalConstants.ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL;

@RestController
@Api(tags = "referrals")
public class ReferralController {
    @Autowired
    private ReferralHandler referralHandler;

    @PostMapping("/referral/email")
    @ApiOperation(value = "Send a referral by email")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ERROR_MSG_UNABLE_TO_SEND_REFERRAL_EMAIL)
    })
    public ResponseEntity<BaseResponseDTO> sendReferralEmail(@Validated @RequestBody ReferralDTO referralDTO) {
        return ResponseEntity.ok()
                .body(referralHandler.sendReferralByEmail(referralDTO));
    }
}
