package com.login.demo.controller;

import com.login.demo.dto.HistoryResponse;
import com.login.demo.dto.PeriodRequest;
import com.login.demo.entity.PeriodRecord;
import com.login.demo.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/period")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PeriodController {
    private final PeriodService periodService;
    @PostMapping
    public String savePeriod(@RequestBody PeriodRequest request, Authentication authentication){
        return periodService.savePeriod(request,authentication.getName());

    }
    @GetMapping("/latest")
    public PeriodRecord getLatestRecord(Authentication authentication){
        return periodService.getLatestRecord(authentication.getName());
    }
    @GetMapping("/history")
    public List<HistoryResponse> getHistory(Authentication authentication){
        return periodService.getHistory(authentication.getName());
    }
    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable Long id){
        periodService.deleteHistory(id);
        return "deleted";
    }
//    @PutMapping("/details")
//    public String updateDetails(@RequestBody PeriodRequest request,Authentication authentication){
//        return periodService.updateCycleDetails(
//                authentication.getName(),
//                request.getSymptoms(),
//                request.getFlow(),
//                request.getNotes()
//        );
//
//    }
    @PutMapping("/details")
    public String updateDetails(@RequestBody PeriodRequest request, Authentication auth){
        System.out.println("DETAILS ENDPOINT HIT");
        return periodService.updateDetails(request,auth.getName());
    }
}
