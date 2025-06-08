package com.Hackason.BankAccountProject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易记录DTO
 */
@Data
public class TransactionRecord {

    private String transactionId;
    private String transactionType;
    private String counterpartyAccount;
    private BigDecimal amount;
    private String currencyType;
    private String status;
    private LocalDateTime transactionTime;
    private String description;
}
