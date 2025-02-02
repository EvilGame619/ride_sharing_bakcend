package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.WalletTransactions;
import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;
import com.Uber.UberApplicaiton.entities.enums.TransactionType;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.repository.WalletRepository;
import com.Uber.UberApplicaiton.services.WalletService;
import com.Uber.UberApplicaiton.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class WalletServiceImple implements WalletService {

    private final ModelMapper mapper;
    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    @Transactional
    @Override
    public Wallet addMoneyToWallet(User user, Double amount, String transactionID, Ride ride, TransactionMethod transactionMethod) {

        Wallet wallet = findByUser(user);
        Double balance = wallet.getBalance();
        wallet.setBalance(balance+amount);

        WalletTransactions walletTransactions = WalletTransactions.builder()
                .ride(ride)
                .transactionID(transactionID)
                .wallet(wallet)
                .amount(amount)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .build();
        System.out.println("wallet transaction exist");
        walletTransactionService.createNewWalletTransaction(walletTransactions);
        return wallet;
    }

    @Transactional
    @Override
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionID, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        Double balance = wallet.getBalance();
        wallet.setBalance(balance-amount);
        WalletTransactions walletTransactions = WalletTransactions.builder()
                .ride(ride)
                .transactionID(transactionID)
                .wallet(wallet)
                .amount(amount)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransactions);
        return wallet;
    }

    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = Wallet.builder().user(user).build();
        return walletRepository.save(wallet);

    }

    @Override
    public Wallet findWalletByID(Long walletID) {
        return walletRepository.findById(walletID).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with ID: "+ walletID));
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with userID: "+user.getUserID()));
    }
}
