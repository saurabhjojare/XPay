package com.xpay.transactions.repository;

import com.xpay.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findBySenderWalletId(String senderWalletId);

    List<Transaction> findByReceiverWalletId(String receiverWalletId);

    List<Transaction> findBySenderWalletIdOrReceiverWalletId(String senderWalletId, String receiverWalletId);
}
