package com.login.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PeriodRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private Integer cycleLength;
    private Integer periodDuration;
    private LocalDate nextPeriodDate;
    private String userEmail;
    @ElementCollection
    private List<String> symptoms;
    private String flow;
    @Column(length = 1000)
    private String notes;


}
