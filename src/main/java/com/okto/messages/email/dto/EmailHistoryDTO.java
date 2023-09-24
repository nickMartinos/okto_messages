package com.okto.messages.email.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailHistoryDTO {
    @NotNull
    @NotEmpty
    private final String startDate; // Mandatory value. Cannot be null or empty.

    @NotNull
    @NotEmpty
    private final String endDate; // Same as startdate.

    private final String emailRecipient;
    private final Integer page;
    private final Integer count;

    public EmailHistoryDTO(String startDate, String endDate, String emailRecipient, Integer page, Integer count) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.emailRecipient = emailRecipient;
        this.page = page;
        this.count = count;
    }

    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getEmailRecipient() { return emailRecipient; }
    public Integer getPage() { return page; }
    public Integer getCount() { return count; }
}
