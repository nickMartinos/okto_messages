package com.okto.mock_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("responseSuccess")
    private boolean responseSuccess;

    public ResponseDto(String responseMessage, boolean responseSuccess) {
        this.responseMessage = responseMessage;
        this.responseSuccess = responseSuccess;
    }
}
