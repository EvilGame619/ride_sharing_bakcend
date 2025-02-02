package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;
import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;
import com.Uber.UberApplicaiton.services.PaymentService;
import com.Uber.UberApplicaiton.services.WalletService;
import com.Uber.UberApplicaiton.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//this is a cash strategy, in which the driver will get the cash in hand, so we just only need to deduct the platform commission from the drivers wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;


    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double paymentCommission = PLATFORM_FEES*payment.getAmount();
        walletService.deductMoneyFromWallet(driver.getUser(), paymentCommission, null,payment.getRide(),TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.DONE);
    }
}
