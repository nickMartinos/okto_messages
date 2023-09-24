package com.okto.messages.sms.repository;

import com.okto.messages.sms.model.SmsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface SmsRepository extends CrudRepository<SmsModel, Integer> {
    @Query("select s from SmsModel s where s.dateSent between ?1 and ?2 and (?3 is null or s.phoneNumber = ?3)")
    Page<SmsModel> findAllByDateSentBetweenAndPhoneNumberEquals(LocalDateTime startDate, LocalDateTime endDate, String phoneNumber, Pageable pageable);
}
