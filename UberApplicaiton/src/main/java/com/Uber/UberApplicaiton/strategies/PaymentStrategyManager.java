package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.entities.enums.PaymentMethod;
import com.Uber.UberApplicaiton.strategies.impl.CashPaymentStrategy;
import com.Uber.UberApplicaiton.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };
    }

}
