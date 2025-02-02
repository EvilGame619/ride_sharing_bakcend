package com.Uber.UberApplicaiton.dto;

import com.Uber.UberApplicaiton.entities.WalletTransactions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {
    private Long walletID;


    private UserDTO user;

    private Double balance;


    private List<WalletTransactions> transactions;
}
