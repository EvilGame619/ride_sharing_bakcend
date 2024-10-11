package com.Uber.UberApplicaiton.entities;

import com.Uber.UberApplicaiton.entities.enums.TransactionMethod;
import com.Uber.UberApplicaiton.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletTransactionID;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(value = EnumType.STRING)
    private TransactionMethod transactionMethod;

    @OneToOne
    private Ride ride;

    private String transactionID;

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;
}