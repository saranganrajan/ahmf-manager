package com.saranganrajan.apps.ahmfmanager.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyTransactionRepository extends JpaRepository<PolicyTransactionEntity, String> {
    @Query(value="SELECT * FROM ahmf_policy_transaction p WHERE p.status = ?1", nativeQuery = true)
    List<PolicyTransactionEntity> findByStatus(String status);

    @Query(value="SELECT * FROM ahmf_policy_transaction p WHERE p.status <> ?1", nativeQuery = true)
    List<PolicyTransactionEntity> findByStatusNotIn(String status);
}
