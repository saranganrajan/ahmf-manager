package com.saranganrajan.apps.ahmfmanager.service.recovery;

import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecoveryService {
    int replayTransactions(List<PolicyTransactionEntity> policyTransactions);
}
