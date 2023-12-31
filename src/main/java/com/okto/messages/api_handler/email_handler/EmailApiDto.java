package com.okto.messages.api_handler.email_handler;

public class EmailApiDto {
    private final String destination;
    private final String subject;
    private final String body;

    public EmailApiDto(String destination, String subject, String body) {
        this.destination = destination;
        this.subject = subject;
        this.body = body;
    }

    public String getDestination() { return destination; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
}
