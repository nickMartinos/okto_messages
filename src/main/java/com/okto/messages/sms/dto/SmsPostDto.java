package com.okto.messages.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SmsPostDto {
    @NotEmpty
    @NotNull
    @Size(min = 1, message = "Phone number cannot be empty.")
    private final String phoneNumber; // Mandatory value. cannot be null, empty, or of size 1.

    @NotEmpty
    @NotNull
    @Size(min = 1, message = "Message cannot be empty.")
    private final String message; // Same as phone number.

    public SmsPostDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public String getMessage() { return message; }
}
