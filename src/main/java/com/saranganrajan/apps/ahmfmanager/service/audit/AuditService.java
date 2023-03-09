package com.saranganrajan.apps.ahmfmanager.service.audit;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditService {
    List<PolicyTransactionEntity> saveTransactions(List<PolicyTransactionEntity> policyTransactions);
    int updateStatus(String transactionId, String status);
}
