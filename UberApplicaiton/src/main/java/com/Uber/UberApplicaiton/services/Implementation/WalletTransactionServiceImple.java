package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.WalletTransactionDTO;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.WalletTransactions;
import com.Uber.UberApplicaiton.repository.WalletTransactionRepository;
import com.Uber.UberApplicaiton.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class WalletTransactionServiceImple implements WalletTransactionService {

    private final ModelMapper mapper;
    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransactions walletTransaction) {
       walletTransactionRepository.save(walletTransaction);
    }
}
