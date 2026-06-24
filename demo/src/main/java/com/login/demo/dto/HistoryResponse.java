package com.login.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String month;
    private LocalDate startDate;
    private Integer cycleLength;
    private Integer periodDuration;
    private LocalDate expectedDate;
    private Integer delayDays;
    private List<String> symptoms;
    private String flow;
    private String notes;

}
