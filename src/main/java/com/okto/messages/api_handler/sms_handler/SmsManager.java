/*
    This class serves as a controller for communications between the API end-point and our asset.
    By using a separate class we are able to make changes to this class alone when necessary and when errors appear.
    This would save us the necessity of changing all individual calls to the API.

    Similar to #EmailManager
 */

package com.okto.messages.api_handler.sms_handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class SmsManager {

    public SmsManager() {
        // Pass in API key -- to then be validated and sent along with sendSMS.
    }

    public SmsResponse sendSMS(String phoneNumber, String message) {
        // POST phoneNumber and message to API endpoint.
        // Verify APIKey integrity before any other check.

        if (phoneNumber.length() <= 3) {
            // Phone number provided too short.
            return new SmsResponse("Phone number provided is too short.", false);
        }

        // Now sending the request to the API endpoint. All variables should be encapsulated and secured (characters escaped) to ensure safety,
        // however, safety is not our priority in this case.

        SmsResponse apiResponse = WebClient
                .builder()
                .baseUrl("http://localhost:8081/api/sms/smsServiceProvider")
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new SmsApiDto(phoneNumber, message)))
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();

        // Ensuring the response from the API endpoint is valid & successful.

        if (apiResponse != null && apiResponse.getResponseSuccess()) {
            System.out.format("SMS sent to number %s\nMessage: %s", phoneNumber, message);
        }

        // Return the response.
        return apiResponse;
    }
}
