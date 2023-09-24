package com.okto.messages.api_handler.sms_handler;

public class SmsApiDto {
    private final String phoneNumber;
    private final String message;

    public SmsApiDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public String getMessage() { return message; }
}
