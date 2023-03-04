package com.saranganrajan.apps.ahmfmanager.external.audit.feign;

import com.saranganrajan.apps.ahmfmanager.modal.TransactionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "audit.service", url="http://localhost:9091")
public interface AuditServiceFeignClient {
    @PostMapping(path = "/audit/transaction/add")
    ResponseEntity<List<TransactionDetails>> addTransactionDetails(@RequestBody List<TransactionDetails> transactions);
}
