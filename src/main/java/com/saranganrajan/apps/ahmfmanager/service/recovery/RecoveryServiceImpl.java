package com.saranganrajan.apps.ahmfmanager.service.recovery;

import com.saranganrajan.apps.ahmfmanager.callback.ReplayCallbackFeign;
import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionEntity;
import com.saranganrajan.apps.ahmfmanager.database.PolicyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecoveryServiceImpl implements RecoveryService {

    @Autowired
    PolicyTransactionRepository policyTransactionRepository;

    @Autowired
    ReplayCallbackFeign replayCallbackFeign;

    public RecoveryServiceImpl(PolicyTransactionRepository policyTransactionRepository, ReplayCallbackFeign replayCallbackFeign) {
        this.policyTransactionRepository = policyTransactionRepository;
        this.replayCallbackFeign = replayCallbackFeign;
    }

    @Override
    public int replayTransactions(List<PolicyTransactionEntity> policyTransactions) {
        replayCallbackFeign.replayCallback(policyTransactions);
        return 0;
    }
}
