package com.okto.messages.email.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailPostDTO {
    @NotNull
    @NotEmpty
    private final String recipient; // Mandatory value. Cannot be null or empty.

    @NotNull
    @NotEmpty
    private final String subject; // Same as startdate.

    @NotNull
    @NotEmpty
    private final String message;

    public EmailPostDTO(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
}
