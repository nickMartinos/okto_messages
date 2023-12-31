package com.okto.mock_api.sms;

import com.okto.mock_api.dto.ResponseDto;
import com.okto.mock_api.dto.SmsReceivedDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/api/sms")
public class SmsController {

    @PostMapping(path = "/smsServiceProvider", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseDto> onSMSReceived(@Valid @RequestBody SmsReceivedDto smsReceivedDTO) {
        /*
            Normally we would have to also parse the API token. validating token would be another thing to do.
            However, on request, we are sticking to the minimalistic approach of only printing the request.
            We are always returning 'true' to always mimic a positive response from the API.
         */

        System.out.format("SMS received at endpoint.\n\nPhone number: %s\nMessage: %s", smsReceivedDTO.getPhoneNumber(), smsReceivedDTO.getMessage());
        return new ResponseEntity<>(new ResponseDto("Validation successful. SMS has been sent.", true), HttpStatus.OK);
    }
}
