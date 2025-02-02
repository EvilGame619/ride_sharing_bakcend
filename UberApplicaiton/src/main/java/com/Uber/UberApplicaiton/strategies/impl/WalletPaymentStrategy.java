package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;
import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;
import com.Uber.UberApplicaiton.repository.WalletRepository;
import com.Uber.UberApplicaiton.services.PaymentService;
import com.Uber.UberApplicaiton.services.WalletService;
import com.Uber.UberApplicaiton.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;


    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        Wallet driverWallet = walletService.findByUser(driver.getUser());
        System.out.println("wallet strategy m");
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null,payment.getRide(),TransactionMethod.RIDE);
        System.out.println("Deduct");
        double paymentCommission = PLATFORM_FEES*payment.getAmount();
        walletService.addMoneyToWallet(driver.getUser(), payment.getAmount()-paymentCommission, null, payment.getRide(), TransactionMethod.RIDE);
        System.out.println("add money to wallet");
        payment.setPaymentStatus(PaymentStatus.DONE);
    }
}
