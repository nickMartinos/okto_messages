/*
    An interface that unifies the responses from each API handler (EmailManager & SMSManager).
    Ensures all responses are handled in similar fashion.
 */

package com.okto.messages.api_handler.interfaces;

public interface CommunicationResponseInterface {
    String getResponseMessage(); // function that returns response message.
    boolean getResponseSuccess(); // function that returns true if request is successful, false otherwise.
}
