package com.okto.messages.email.repository;

import com.okto.messages.email.model.EmailModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EmailRepository extends CrudRepository<EmailModel, Integer> {
    @Query("select e from EmailModel e where e.dateSent between ?1 and ?2 and (?3 is null or e.recipient = ?3)")
    Page<EmailModel> findAllByDateSentBetweenAndRecipientEquals(LocalDateTime startDate, LocalDateTime endDate, String recipient, Pageable pageable);
}
