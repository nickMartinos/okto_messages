package com.okto.messages.email.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class EmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Auto incrementing ID

    private String recipient;
    private String subject;
    private String message;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateSent = LocalDateTime.now();

    public Integer getEmailId() {
        return id;
    }

    public String getEmailRecipient() {
        return recipient;
    }

    public String getEmailSubject() {
        return subject;
    }

    public String getEmailMessage() { return message; }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setEmailRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setEmailSubject(String subject) { this.subject = subject; }

    public void setEmailMessage(String message) { this.message = message; }

    public void updateSentDate() {
        /*
        * Updating to current datetime, same as SMS system.
        */
        dateSent = LocalDateTime.now();
    }
}
