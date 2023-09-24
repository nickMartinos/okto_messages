/*
    This class is a custom response type for our mock API.
    We assume the response from the API is of similar fashion.
    However, it also allows us to customize the response from the API to include/exclude data, should we want to.

    Similar to #EmailResponse
*/

package com.okto.messages.api_handler.sms_handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.okto.messages.api_handler.interfaces.CommunicationResponseInterface;

public class SMSResponse implements CommunicationResponseInterface {
    @JsonProperty("responseMessage")
    String responseMessage;

    @JsonProperty("responseSuccess")
    boolean responseSuccess;

    public SMSResponse(String responseMessage, boolean responseSuccess) {
        this.responseMessage = responseMessage;
        this.responseSuccess = responseSuccess;
    }

    public SMSResponse() { }

    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean getResponseSuccess() {
        return responseSuccess;
    }
}
