package com.Hackason.BankAccountProject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户信息响应DTO
 */
@Data
public class AccountInfoResponse {
    private String accountId;
    private String username;
    private String nickname;
    private String cardId;
    private List<BalanceInfo> balances;
    private List<TransactionRecord> transactions;

    @Data
    public static class BalanceInfo {
        private BigDecimal amount;
        private String currency;
    }
}
