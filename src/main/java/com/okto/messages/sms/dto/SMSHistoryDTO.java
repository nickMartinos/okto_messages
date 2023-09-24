package com.okto.messages.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SMSHistoryDTO {
    @NotNull
    @NotEmpty
    private final String startDate; // Mandatory value. Cannot be null or empty.

    @NotNull
    @NotEmpty
    private final String endDate; // Same as startdate.

    private final String phoneNumber;
    private final Integer page;
    private final Integer count;

    public SMSHistoryDTO(String startDate, String endDate, String phoneNumber, Integer page, Integer count) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
        this.page = page;
        this.count = count;
    }

    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public Integer getPage() { return page; }
    public Integer getCount() { return count; }
}
