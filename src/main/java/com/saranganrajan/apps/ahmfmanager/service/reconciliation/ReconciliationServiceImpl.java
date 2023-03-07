package com.saranganrajan.apps.ahmfmanager.service.reconciliation;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReconciliationServiceImpl implements ReconciliationService {

    @Autowired
    PolicyTransactionRepository policyTransactionRepository;

    public ReconciliationServiceImpl(PolicyTransactionRepository policyTransactionRepository) {
        this.policyTransactionRepository = policyTransactionRepository;
    }


    @Override
    public List<PolicyTransactionEntity> getIncompleteTransactions() {
        return policyTransactionRepository.findByStatusNotIn("COMPLETED");
    }

    @Override
    public int cleanUpCompletedTransactions() {
        List<PolicyTransactionEntity> deletableTransactions = policyTransactionRepository.findByStatus("COMPLETED");
        policyTransactionRepository.deleteAll(deletableTransactions);
        return deletableTransactions.size();
    }
}
