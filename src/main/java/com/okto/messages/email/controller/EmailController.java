package com.okto.messages.email.controller;

import com.okto.messages.api_handler.email_handler.EmailManager;
import com.okto.messages.api_handler.email_handler.EmailResponse;
import com.okto.messages.email.dto.EmailHistoryDTO;
import com.okto.messages.email.dto.EmailPostDTO;
import com.okto.messages.email.model.EmailModel;
import com.okto.messages.email.repository.EmailRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(path="/email")
public class EmailController {
    private final EmailRepository emailRepository;
    private final EmailManager emailManager;

    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository; // passing in the email repository.
        emailManager = new EmailManager(); // Initializing the email API controller.
    }

    @GetMapping(path="/history")
    public ResponseEntity<Page<EmailModel>> getMessageHistory(@Valid @RequestBody EmailHistoryDTO emailHistoryDTO) {

        // Converting date to a LocalDateTime format.
        LocalDateTime formattedStartDate = LocalDate.parse(emailHistoryDTO.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        LocalDateTime formattedEndDate = LocalDate.parse(emailHistoryDTO.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(LocalTime.MAX); // End of day for chosen end date

        Pageable pagedResults;

        if (emailHistoryDTO.getPage() != null && emailHistoryDTO.getCount() != null) {
            pagedResults = PageRequest.of(emailHistoryDTO.getPage(), emailHistoryDTO.getCount());
        } else {
            pagedResults = Pageable.unpaged();
        }

        Page<EmailModel> emailHistory = emailRepository.findAllByDateSentBetweenAndRecipientEquals(formattedStartDate, formattedEndDate, emailHistoryDTO.getEmailRecipient(), pagedResults);

        if (emailHistory == null || emailHistory.getContent().size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // No response data, thus returning NOT_FOUND to be handled by front-end.
        } else {
            return new ResponseEntity<>(emailHistory, HttpStatus.OK); // Response data found. Returning 200 status along with the data.
        }
    }

    @PostMapping(path="/send")
    public ResponseEntity<EmailModel> sendSMS(@Valid @RequestBody EmailPostDTO emailPostDTO) {
        EmailResponse emailResponse = emailManager.sendEmail(emailPostDTO.getRecipient(), emailPostDTO.getSubject(), emailPostDTO.getMessage());

        if (emailResponse.getResponseSuccess()) {
            // Since the message was actually sent, and it was successful, we can now create the new SMS model and add it to the database.
            // If we had a "failed" flag on the database, we could have added it in case of a failed response as well (more filtering options).

            EmailModel emailModel = new EmailModel();
            emailModel.setEmailMessage(emailPostDTO.getMessage());
            emailModel.setEmailSubject(emailPostDTO.getSubject());
            emailModel.setEmailRecipient(emailPostDTO.getRecipient());
            emailRepository.save(emailModel);

            System.out.print(emailResponse.getResponseMessage());
            return new ResponseEntity<>(emailModel, HttpStatus.CREATED); // Responding with the newly created model, along with the CREATED status to be handled accordingly.
        } else {
            // Email could not be sent for whatever reason -- we send the bad request, print the reason why the email could not be sent.
            System.out.print(emailResponse.getResponseMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Bad request as something went wrong -- returning null.
        }
    }
}
