package com.saranganrajan.apps.ahmfmanager.service.audit;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AuditServiceImpl implements AuditService {

    @Autowired
    PolicyTransactionRepository policyTransactionRepository;

    public AuditServiceImpl(PolicyTransactionRepository policyTransactionRepository) {
        this.policyTransactionRepository = policyTransactionRepository;
    }

    @Override
    public List<PolicyTransactionEntity> saveTransactions(List<PolicyTransactionEntity> policyTransactions) {
        log.info("Saving {} transactions", policyTransactions.size());
        List<PolicyTransactionEntity> updatedEntities = policyTransactionRepository.saveAll(policyTransactions);
        return updatedEntities;
    }

    @Override
    public int updateStatus(String transactionId, String status) {
        Optional<PolicyTransactionEntity> policyTransactionEntity = policyTransactionRepository.findById(transactionId);
        if (policyTransactionEntity.isPresent()) {
            policyTransactionEntity.get().setStatus(status);
            policyTransactionRepository.save(policyTransactionEntity.get());
            log.info("Updated status of transaction {} to {}", transactionId, status);
            return 1;
        }
        return 0;
    }

}
