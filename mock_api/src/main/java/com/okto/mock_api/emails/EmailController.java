package com.okto.mock_api.emails;

import com.okto.mock_api.dto.EmailReceivedDto;
import com.okto.mock_api.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/api/emails")
public class EmailController {

    @PostMapping(path = "/emailServiceProvider", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseDto> onEmailReceived(@Valid @RequestBody EmailReceivedDto emailReceivedDTO) {
        /*
            Normally we would have to also parse the API token. validating token would be another thing to do.
            However, on request, we are sticking to the minimalistic approach of only printing the request.
            We are always returning 'true' to always mimic a positive response from the API.
         */

        System.out.format("Email received at endpoint.\n\nDestination: %s\nSubject: %s\nBody: %s", emailReceivedDTO.getDestination(), emailReceivedDTO.getSubject(), emailReceivedDTO.getBody());
        return new ResponseEntity<>(new ResponseDto("Validation successful. Email has been sent.", true), HttpStatus.OK);
    }
}
