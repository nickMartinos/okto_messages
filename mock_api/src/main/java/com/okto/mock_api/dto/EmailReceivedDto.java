package com.okto.mock_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailReceivedDto {
    @NotNull
    @NotEmpty
    private final String destination;

    @NotNull
    @NotEmpty
    private final String subject;

    @NotEmpty
    @NotEmpty
    private final String body;

    public EmailReceivedDto(String destination, String subject, String body) {
        this.destination = destination;
        this.subject = subject;
        this.body = body;
    }

    public String getBody() { return body; }
    public String getDestination() { return destination; }
    public String getSubject() { return subject; }
}
