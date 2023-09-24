package com.okto.messages.sms.repository;

import com.okto.messages.sms.model.SMSModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface SMSRepository extends CrudRepository<SMSModel, Integer> {
    @Query("select s from SMSModel s where s.dateSent between ?1 and ?2 and (?3 is null or s.phoneNumber = ?3)")
    Page<SMSModel> findAllByDateSentBetweenAndPhoneNumberEquals(LocalDateTime startDate, LocalDateTime endDate, String phoneNumber, Pageable pageable);
}
