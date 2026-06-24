package com.login.demo.service;

import com.login.demo.dto.HistoryResponse;
import com.login.demo.dto.PeriodRequest;
import com.login.demo.entity.PeriodRecord;
import com.login.demo.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodService {
    private final PeriodRepository periodRepository;

    //    public String savePeriod(PeriodRequest request, String email){
//        LocalDate nextPeriod = request.getStartDate()
//                .plusDays(request.getCycleLength());
//
//        PeriodRecord record = new PeriodRecord();
//
//        record.setStartDate(request.getStartDate());
//        record.setCycleLength(request.getCycleLength());
//        record.setPeriodDuration(request.getPeriodDuration());
//        record.setNextPeriodDate(nextPeriod);
//        record.setUserEmail(email);
//        periodRepository.save(record);
//        return "Period Data saved Successfully";
//    }
    public String savePeriod(PeriodRequest request, String email) {
        LocalDate nextPeriod = request
                .getStartDate()
                .plusDays(request.getCycleLength());
        List<PeriodRecord> records = periodRepository.findByUserEmailOrderByStartDateAsc(email);
        PeriodRecord existing = null;
        for (PeriodRecord r : records) {
            if (r.getStartDate()
                    .getMonth()
                    .equals(request.getStartDate()
                            .getMonth()) && r.getStartDate()
                    .getYear()
                    == request.getStartDate()
                    .getYear()) {
                existing = r;
                break;
            }
        }
        PeriodRecord record = existing != null ? existing : new PeriodRecord();
        record.setUserEmail(email);
        record.setStartDate(request.getStartDate());
        record.setCycleLength(request.getCycleLength());
        record.setPeriodDuration(request.getPeriodDuration());
//    record.setNextPeriodDate(nextPeriod);
        record.setSymptoms(request.getSymptoms());
        record.setFlow(request.getFlow());
        record.setNotes(request.getNotes());
        record.setNextPeriodDate(nextPeriod);
        periodRepository.save(record);
        System.out.println(request.getSymptoms());
        System.out.println(request.getFlow());
        System.out.println(request.getNotes());

        return existing != null ? "Month Updated" : "NewMonth Added";

    }

    public PeriodRecord getLatestRecord(String email) {
        return periodRepository.findTopByUserEmailOrderByIdDesc(email)
                .orElse(null);
    }

    public List<HistoryResponse> getHistory(String email) {

        List<PeriodRecord> records = periodRepository.findByUserEmailOrderByStartDateAsc(email);
        List<HistoryResponse> history = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            PeriodRecord current = records.get(i);

            System.out.println(
                    "HISTORY DATA"
            );

            System.out.println(
                    current.getSymptoms()
            );

            System.out.println(
                    current.getFlow()
            );

            System.out.println(
                    current.getNotes()
            );

            LocalDate expectedDate = null;
            int delayDays = 0;
            if (i > 0) {
                PeriodRecord previous = records.get(i - 1);
                expectedDate = previous.getStartDate()
                        .plusDays(previous.getCycleLength());
                delayDays = (int) ChronoUnit.DAYS.between(expectedDate, current.getStartDate());
            }
            System.out.println("RETURNING");

            System.out.println(current.getSymptoms());

            System.out.println(current.getFlow());

            System.out.println(current.getNotes());

            history.add(new HistoryResponse(current.getId(), current.getStartDate()
                            .getMonth()
                            .toString(),
                            current.getStartDate(),
                            current.getCycleLength(),
                            current.getPeriodDuration(),
                            expectedDate,
                            delayDays,
                            current.getSymptoms(),
                            current.getFlow(),
                            current.getNotes()
                    )
            );
        }
        Collections.reverse(history);
        return history;
    }

    public void deleteHistory(Long id) {
        periodRepository.deleteById(id);

    }
//    public String updateDetails(PeriodRequest request,String email){
//    List<PeriodRecord> records = periodRepository.findTopByUserEmailOrderByStartDateDesc(email);
//    for(PeriodRecord r: records){
//        if(r.getStartDate()
//                .getMonth()
//                .equals(request.getStartDate().getMonth())
//        && r.getStartDate()
//                .getYear() == request.getStartDate().getYear()){
//            r.setSymptoms(request.getSymptoms());
//            r.setFlow(request.getFlow());
//            r.setNotes(request.getNotes());
//            periodRepository.save(r);
//            return "updated";
//        }
//    }
//    return "No Record";
//    }


//public String updateCycleDetails(
//        String email,
//        List<String> symptoms,
//        String flow,
//        String notes){
//
//    PeriodRecord record = periodRepository
//                    .findTopByUserEmailOrderByIdDesc(email)
//                    .orElse(null);
//    if(record == null){
//        return "No cycle found";
//    }
//    record.setSymptoms(symptoms);
//    record.setFlow(flow);
//    record.setNotes(notes);
//    periodRepository.save(record);
//    return "Details Updated";
//}
//
//public String updateDetails(PeriodRequest request, String email){
//    List<PeriodRecord> records = periodRepository.findTopByUserEmailOrderByStartDateDesc(email);
//    for(PeriodRecord r :records){
//        if(
//                r.getStartDate()
//                        .getMonth()
//                        .equals(request.getStartDate().getMonth())
//                &&
//                        r.getStartDate()
//                                .getYear() == request.getStartDate().getYear()
//        ){
//            r.setSymptoms(request.getSymptoms());
//            r.setFlow(request.getFlow());
//            r.setNotes(request.getNotes());
//            periodRepository.save(r);
//            return "updated";
//        }
//    }
//    return "Month not found";
//}
public String updateDetails(PeriodRequest request, String email
){

    System.out.println("SERVICE HIT");

    System.out.println(request.getStartDate());

    System.out.println(request.getSymptoms());

    System.out.println(request.getFlow());

    System.out.println(request.getNotes());

    List<PeriodRecord> records =
            periodRepository
                    .findByUserEmailOrderByStartDateAsc(email);

    for(PeriodRecord r : records){

        System.out.println(
                "DB DATE:"
                        +
                        r.getStartDate()
        );

        if(

                r.getStartDate()
                        .getMonth()

                        .equals(
                                request
                                        .getStartDate()
                                        .getMonth()
                        )

                        &&

                        r.getStartDate()
                                .getYear()

                                ==

                                request
                                        .getStartDate()
                                        .getYear()

        ){

            System.out.println(
                    "MATCH FOUND"
            );

            r.setSymptoms(
                    request.getSymptoms()
            );

            r.setFlow(
                    request.getFlow()
            );

            r.setNotes(
                    request.getNotes()
            );

            periodRepository.save(r);

            System.out.println(
                    "SAVED"
            );

            return "Updated";

        }

    }

    return "Month Not Found";

}
}
