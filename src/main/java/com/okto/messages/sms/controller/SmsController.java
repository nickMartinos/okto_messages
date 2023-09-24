package com.okto.messages.sms.controller;

import com.okto.messages.api_handler.sms_handler.SmsManager;
import com.okto.messages.api_handler.sms_handler.SmsResponse;
import com.okto.messages.sms.dto.SmsHistoryDto;
import com.okto.messages.sms.dto.SmsPostDto;
import com.okto.messages.sms.model.SmsModel;
import com.okto.messages.sms.repository.SmsRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path="/sms")
public class SmsController {
    private final SmsRepository smsRepository;
    private final SmsManager smsManager;

    public SmsController(SmsRepository smsRepository) {
        this.smsRepository = smsRepository; // Initializing SMS repository.
        smsManager = new SmsManager(); // Initializing our SMS manager API controller.
    }

    @GetMapping(path="/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SmsModel>> getMessageHistory(@Valid @RequestBody SmsHistoryDto smsHistoryDTO) {

        // Converting date to a LocalDateTime format.
        LocalDateTime formattedStartDate = LocalDate.parse(smsHistoryDTO.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        LocalDateTime formattedEndDate = LocalDate.parse(smsHistoryDTO.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(LocalTime.MAX); // End of day for chosen end date

        // Paginating the results; declaring to then change.
        Pageable pagedResults;

        if (smsHistoryDTO.getPage() != null && smsHistoryDTO.getCount() != null) { // Page the results only if both the 'page' and 'count' are provided.
            pagedResults = PageRequest.of(smsHistoryDTO.getPage(), smsHistoryDTO.getCount());
        } else {
            pagedResults = Pageable.unpaged();
        }

        Page<SmsModel> smsHistory = smsRepository.findAllByDateSentBetweenAndPhoneNumberEquals(formattedStartDate, formattedEndDate, smsHistoryDTO.getPhoneNumber(), pagedResults);

        if (smsHistory == null || smsHistory.getContent().size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // No response data, thus returning NOT_FOUND to be handled by front-end.
        } else {
            return new ResponseEntity<>(smsHistory, HttpStatus.OK); // Response data found. Returning 200 status along with the data.
        }
    }

    // Body content is validated through the @Valid annotation.
    @PostMapping(path="/send")
    public ResponseEntity<SmsModel> sendSMS(@Valid @RequestBody SmsPostDto smsPostDTO) {
        SmsResponse smsResponse = smsManager.sendSMS(smsPostDTO.getPhoneNumber(), smsPostDTO.getMessage());

        if (smsResponse.getResponseSuccess()) {
            // Since the message was actually sent, and it was successful, we can now create the new SMS model and add it to the database.
            // If we had a "failed" flag on the database, we could have added it in case of a failed response as well (more filtering options).

            SmsModel newSMS = new SmsModel();
            newSMS.setMessage(smsPostDTO.getPhoneNumber());
            newSMS.setPhoneNumber(smsPostDTO.getMessage());
            smsRepository.save(newSMS);

            System.out.println(smsResponse.getResponseMessage());
            return new ResponseEntity<>(newSMS, HttpStatus.CREATED); // Responding with the newly created model, along with the CREATED status to be handled accordingly.
        } else {
            // SMS could not be sent.
            System.out.println(smsResponse.getResponseMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Bad request as something went wrong -- returning null.
        }
    }
}
