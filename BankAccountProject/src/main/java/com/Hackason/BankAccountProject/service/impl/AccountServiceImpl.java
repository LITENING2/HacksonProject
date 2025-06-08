package com.Hackason.BankAccountProject.service.impl;

import com.Hackason.BankAccountProject.dto.AccountInfoResponse;
import com.Hackason.BankAccountProject.dto.TransactionRecord;
import com.Hackason.BankAccountProject.entity.Balance;
import com.Hackason.BankAccountProject.entity.TransactionStatus;
import com.Hackason.BankAccountProject.entity.User;
import com.Hackason.BankAccountProject.mapper.BalanceMapper;
import com.Hackason.BankAccountProject.mapper.TransactionStatusMapper;
import com.Hackason.BankAccountProject.mapper.UserMapper;
import com.Hackason.BankAccountProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户服务实现类
 */
@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private BalanceMapper balanceMapper;
    
    @Autowired
    private TransactionStatusMapper transactionStatusMapper;
    
    @Override
    public AccountInfoResponse getAccountInfo(String accountId) {
        // 1. 根据账户ID查询用户信息（假设accountId就是cardId）
        User user = userMapper.findByCardId(accountId);
        if (user == null) {
            throw new RuntimeException("账户不存在");
        }
        
        // 2. 构建响应对象
        AccountInfoResponse response = new AccountInfoResponse();
        response.setAccountId(accountId);
        response.setUsername(user.getUser());
        response.setNickname(user.getNickname());
        response.setCardId(user.getCardId());
        
        // 3. 查询余额信息
        try {
            Long accountIdLong = Long.parseLong(accountId);
            List<Balance> balances = balanceMapper.findByAccountId(accountIdLong);
            List<AccountInfoResponse.BalanceInfo> balanceInfos = balances.stream()
                    .map(balance -> {
                        AccountInfoResponse.BalanceInfo balanceInfo = new AccountInfoResponse.BalanceInfo();
                        balanceInfo.setAmount(balance.getAmount());
                        balanceInfo.setCurrency(balance.getCurrency());
                        return balanceInfo;
                    })
                    .collect(Collectors.toList());
            response.setBalances(balanceInfos);
        } catch (NumberFormatException e) {
            AccountInfoResponse.BalanceInfo balanceInfo = new AccountInfoResponse.BalanceInfo();
            if (user.getBalance() != null) {
                try {
                    balanceInfo.setAmount(new java.math.BigDecimal(user.getBalance()));
                } catch (NumberFormatException ex) {
                    balanceInfo.setAmount(java.math.BigDecimal.ZERO);
                }
            } else {
                balanceInfo.setAmount(java.math.BigDecimal.ZERO);
            }
            balanceInfo.setCurrency("CNY"); // 默认人民币
            List<AccountInfoResponse.BalanceInfo> balanceInfos = new ArrayList<>();
            balanceInfos.add(balanceInfo);
            response.setBalances(balanceInfos);
        }
        
        // 4. 查询交易记录
        List<TransactionStatus> transactions = transactionStatusMapper.findByAccount(accountId);
        List<TransactionRecord> transactionRecords = transactions.stream()
                .map(transaction -> {
                    TransactionRecord record = new TransactionRecord();
                    record.setTransactionId(transaction.getTransactionId());
                    record.setAmount(transaction.getAmount());
                    record.setCurrencyType(transaction.getCurrencyType());
                    record.setStatus(transaction.getStatus());
                    record.setTransactionTime(transaction.getCreatedAt());
                    
                    // 判断交易类型和对方账户
                    if (accountId.equals(transaction.getFromAccount())) {
                        record.setTransactionType("转出");
                        record.setCounterpartyAccount(transaction.getToAccount());
                        record.setDescription("转账给 " + transaction.getToAccount());
                    } else {
                        record.setTransactionType("转入");
                        record.setCounterpartyAccount(transaction.getFromAccount());
                        record.setDescription("来自 " + transaction.getFromAccount() + " 的转账");
                    }
                    
                    return record;
                })
                .collect(Collectors.toList());
        
        response.setTransactions(transactionRecords);
        
        return response;
    }
}
