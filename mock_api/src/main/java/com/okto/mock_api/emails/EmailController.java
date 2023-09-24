package com.okto.mock_api.emails;

import com.okto.mock_api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/api/emails")
public class EmailController {

    @PostMapping(path = "/emailServiceProvider", produces = "application/json")
    public ResponseEntity<ResponseDTO> onEmailReceived(@RequestParam String destination, @RequestParam String subject, @RequestParam String body) {
        /*
            Normally we would have to also parse the API token. validating token would be another thing to do.
            However, on request, we are sticking to the minimalistic approach of only printing the request.
            We are always returning 'true' to always mimic a positive response from the API.
         */

        System.out.format("Email received at endpoint.\n\nDestination: %s\nSubject: %s\nBody: %s", destination, subject, body);
        return new ResponseEntity<>(new ResponseDTO("Validation successful. Email has been sent.", true), HttpStatus.OK);
    }
}