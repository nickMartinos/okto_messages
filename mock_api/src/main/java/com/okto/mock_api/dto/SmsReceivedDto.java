package com.okto.mock_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SmsReceivedDto {
    @NotNull
    @NotEmpty
    private final String phoneNumber;

    @NotNull
    @NotEmpty
    private final String message;

    public SmsReceivedDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public String getMessage() { return message; }
}
