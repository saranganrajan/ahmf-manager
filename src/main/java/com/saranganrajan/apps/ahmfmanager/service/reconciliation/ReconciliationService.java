package com.saranganrajan.apps.ahmfmanager.service.reconciliation;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReconciliationService {
    List<PolicyTransactionEntity> getIncompleteTransactions();
    int cleanUpCompletedTransactions();
}
