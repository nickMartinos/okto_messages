/*
    This class is a custom response type for our mock API.
    We assume the response from the API is of similar fashion.
    However, it also allows us to customize the response from the API to include/exclude data, should we want to.

    Similar to #SMSResponse
*/

package com.okto.messages.api_handler.email_handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailResponse {
    @JsonProperty("responseMessage")
    String responseMessage;

    @JsonProperty("responseSuccess")
    boolean responseSuccess;

    public EmailResponse(String responseMessage, boolean responseSuccess) {
        this.responseMessage = responseMessage;
        this.responseSuccess = responseSuccess;
    }

    public EmailResponse() { }

    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean getResponseSuccess() {
        return responseSuccess;
    }
}
