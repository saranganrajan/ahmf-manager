package com.saranganrajan.apps.ahmfmanager.controller;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import com.saranganrajan.apps.ahmfmanager.service.audit.AuditService;
import com.saranganrajan.apps.ahmfmanager.service.reconciliation.ReconciliationService;
import com.saranganrajan.apps.ahmfmanager.service.recovery.RecoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AHMFManager {

    @Autowired
    AuditService auditService;

    @Autowired
    ReconciliationService reconciliationService;

    @Autowired
    RecoveryService recoveryService;

    public AHMFManager(AuditService auditService, ReconciliationService reconciliationService, RecoveryService recoveryService) {
        this.auditService = auditService;
        this.reconciliationService = reconciliationService;
        this.recoveryService = recoveryService;
    }

    @PostMapping(path = "/manager/audit/save", consumes = "application/json")
    ResponseEntity<List<PolicyTransactionEntity>> saveTransactions(@RequestBody List<PolicyTransactionEntity> policyTransactions) {
        return ResponseEntity.ok(auditService.saveTransactions(policyTransactions));
    }

    @PostMapping(path = "/manager/audit/update/{transactionId}/{status}")
    int updateStatus(@PathVariable String transactionId, @PathVariable String status) {
        return auditService.updateStatus(transactionId, status);
    }

    @Scheduled(fixedDelay = 1800000, initialDelay = 1800000)
    public void findExecutableJobs() {
      //Find processable transactions
        List<PolicyTransactionEntity> policyTransactionEntities  = reconciliationService.getIncompleteTransactions();
        if(policyTransactionEntities.isEmpty()) {
log.info("No transactions to process");
            return;
        }
        //Replay transactions
        int replayedTransactions = recoveryService.replayTransactions(policyTransactionEntities);
        log.info("number of transactions to process : " + replayedTransactions);
        //Cleanup
        reconciliationService.cleanUpCompletedTransactions();
    }
}
