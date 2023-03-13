package com.saranganrajan.apps.ahmfmanager.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import com.saranganrajan.apps.ahmfmanager.modal.MaintenanceDetails;
import com.saranganrajan.apps.ahmfmanager.modal.MaintenanceDetailsList;
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

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

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

    @Scheduled(fixedDelay = 30000, initialDelay = 30000)
    public void findExecutableJobs() {
        String system = "Salesforce";
        checkMaintenanceWindow(system);
        checkIfTransactionsExist(system);
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

    private boolean checkIfTransactionsExist(String system) {
        //TODO: Check if transactions exist in the system by checking the JSON File
        return true;
    }

    private boolean checkMaintenanceWindow(String system){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String CurrentDateStr = formatter.format(LocalDateTime.now());
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("/Users/vijay/Saran/Saran_Masters/ahmf-manager/src/main/resources/Maintenance.json"));
            // convert JSON file to map
            Type collectionType = new TypeToken<Collection<MaintenanceDetails>>(){}.getType();
            List<MaintenanceDetails> maintenanceDetailsList = (List<MaintenanceDetails>) gson.fromJson(reader, collectionType);

            for (MaintenanceDetails maintenanceDetails : maintenanceDetailsList) {
                if(system.equals(maintenanceDetails.SystemName)){
                    String FromDateStr = maintenanceDetails.ScheduledMaintenanceWindowFrom;
                    String ToDateStr = maintenanceDetails.ScheduledMaintenanceWindowTo;
                    int c1 = CurrentDateStr.compareTo(FromDateStr);
                    int c2 = CurrentDateStr.compareTo(ToDateStr);
                    if ((c1 >= 0 && c2 <= 0) || (c1 <= 0 && c2 >= 0)) {
                        return true;
                    }
                }
            }
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
