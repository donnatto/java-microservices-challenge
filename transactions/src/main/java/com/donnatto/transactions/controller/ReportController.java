package com.donnatto.transactions.controller;

import com.donnatto.transactions.dto.AccountReportDTO;
import com.donnatto.transactions.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {
    
    private final ReportService service;
    
    @GetMapping
    public List<AccountReportDTO> getCustomerAccountStatus(
            @RequestParam String customerId,
            @RequestParam String from,
            @RequestParam String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime fromDate = LocalDate.parse(from, formatter).atStartOfDay();
        LocalDateTime toDate = LocalDate.parse(to, formatter).atTime(23, 59);
        return service.getCustomerAccountStatus(UUID.fromString(customerId), fromDate, toDate);
    }
}
