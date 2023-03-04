package com.saranganrajan.apps.ahmfmanager.controller;

import com.saranganrajan.apps.ahmfmanager.external.audit.feign.AuditServiceFeignClient;
import com.saranganrajan.apps.ahmfmanager.modal.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class AuditController {
    @Autowired
    AuditServiceFeignClient auditServiceFeignClient;

    @PostMapping(path = "/transaction/add", consumes = "application/json")
    public ResponseEntity<List<TransactionDetails>> AddTransaction(@RequestBody List<TransactionDetails> transactions) {
        ResponseEntity responseEntity = auditServiceFeignClient.addTransactionDetails(transactions);
        return responseEntity;
    }
}
