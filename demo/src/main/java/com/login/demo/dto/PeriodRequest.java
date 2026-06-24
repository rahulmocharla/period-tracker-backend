package com.login.demo.dto;

import com.login.demo.repository.PeriodRepository;

import java.time.LocalDate;
import java.util.List;

public class PeriodRequest {
    private LocalDate startDate;
    private Integer cycleLength;
    private Integer periodDuration;
    private List<String> symptoms;
    private String flow;
    private String notes;


    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(Integer cycleLength) {
        this.cycleLength = cycleLength;
    }

    public Integer getPeriodDuration() {
        return periodDuration;
    }

    public void setPeriodDuration(Integer periodDuration) {
        this.periodDuration = periodDuration;
    }
}
