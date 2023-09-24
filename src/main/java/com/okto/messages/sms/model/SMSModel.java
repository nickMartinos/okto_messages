package com.okto.messages.sms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class SMSModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Auto incrementing ID

    private String phoneNumber;
    private String message;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateSent = LocalDateTime.now();

    public Integer getSMSId() {
        return id;
    }

    public String getSMSPhoneNumber() {
        return phoneNumber;
    }

    public String getSMSMessage() {
        return message;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void updateSentDate() {
        /*
        * Sent date is updated to the current moment it is called (mainly important for when changes are made to
        * the entry).
        */
        dateSent = LocalDateTime.now();
    }
}
