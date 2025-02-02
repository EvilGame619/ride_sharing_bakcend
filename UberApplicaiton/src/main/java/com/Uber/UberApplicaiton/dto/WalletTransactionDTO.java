package com.Uber.UberApplicaiton.dto;

import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.Wallet;
import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;
import com.Uber.UberApplicaiton.entities.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionDTO {
    private Long walletTransactionID;

    private Double amount;


    private TransactionType transactionType;

    private TransactionMethod transactionMethod;


    private RideDTO ride;

    private String transactionID;


    private WalletDTO wallet;


    private LocalDateTime timeStamp;
}
