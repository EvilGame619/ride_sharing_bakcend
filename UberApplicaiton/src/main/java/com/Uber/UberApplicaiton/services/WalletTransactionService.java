package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.WalletTransactionDTO;
import com.Uber.UberApplicaiton.entities.WalletTransactions;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransactions walletTransaction);
}
