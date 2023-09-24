/*
    This class serves as a controller for communications between the API end-point and our asset.
    By using a separate class we are able to make changes to this class alone when necessary and when errors appear.
    This would save us the necessity of changing all individual calls to the API.

    Similar to #SMSManager
 */

package com.okto.messages.api_handler.email_handler;

import org.springframework.web.reactive.function.client.WebClient;

public class EmailManager {
    public EmailManager() {
        // Pass in API key -- to then be validated and sent along with sendEmail.
    }

    public EmailResponse sendEmail(String emailReceipt, String emailSubject, String emailMessage) {
        // POST emailReceipt and emailMessage to API endpoint.
        // Verify APIKey integrity before any other check.

        // Quick validation of input before sending to API.

        if (emailReceipt.length() <= 3) {
            // Email is too short to be a legitimate email address.
            return new EmailResponse("Provided email address is too short.", false);
        }

        if (!emailReceipt.contains("@")) { // Simple validation - would've been better to use regex.
            return new EmailResponse("Provided email address is not valid.", false);
        }

        // Now sending the request to the API endpoint. All variables should be encapsulated and secured (characters escaped) to ensure safety,
        // however, safety is not our priority in this case.

        EmailResponse apiResponse = WebClient
                .builder()
                .baseUrl("http://localhost:8081/api/emails/emailServiceProvider?destination=" + emailReceipt + "&subject=" + emailSubject + "&body=" + emailMessage)
                .build()
                .post()
                .retrieve()
                .bodyToMono(EmailResponse.class)
                .block();

        // Ensuring the response from the API endpoint is valid & successful.

        if (apiResponse != null && apiResponse.getResponseSuccess()) {
            System.out.format("Email sent to address %s\nSubject: %s\nBody: %s", emailSubject, emailReceipt, emailMessage);
        }

        // Return the response.
        return apiResponse;
    }
}
