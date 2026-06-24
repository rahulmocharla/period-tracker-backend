package com.login.demo.repository;

import com.login.demo.entity.PeriodRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeriodRepository extends JpaRepository<PeriodRecord, Long> {

    Optional<PeriodRecord> findTopByUserEmailOrderByIdDesc(String userEmail);
    List<PeriodRecord> findTopByUserEmailOrderByStartDateDesc(String userEmail);

    List<PeriodRecord> findByUserEmailOrderByStartDateAsc(String email);
}
