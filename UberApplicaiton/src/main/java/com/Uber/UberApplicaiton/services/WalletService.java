package com.Uber.UberApplicaiton.services;


import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionID, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionID, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMoneyFromWallet();

    Wallet createNewWallet(User user);

    Wallet findWalletByID(Long walletID);

    Wallet findByUser(User user);
}
