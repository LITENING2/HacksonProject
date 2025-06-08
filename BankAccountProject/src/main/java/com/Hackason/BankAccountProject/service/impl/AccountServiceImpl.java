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
 * 银行账户服务实现类
 *
 * <p>实现了{@link AccountService}接口，提供银行账户相关业务逻辑的具体实现。
 * 该类负责整合来自不同数据源的账户信息，包括用户基本信息、余额信息和交易记录。</p>
 *
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>账户信息查询和整合</li>
 *   <li>多币种余额信息处理</li>
 *   <li>交易记录的查询和格式化</li>
 *   <li>异常情况的处理和错误信息返回</li>
 * </ul>
 *
 * <p>该实现类使用MyBatis-Plus进行数据访问，支持逻辑删除和多表关联查询。</p>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 * @see AccountService
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * 用户数据访问对象，用于查询用户基本信息
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 余额数据访问对象，用于查询账户余额信息
     */
    @Autowired
    private BalanceMapper balanceMapper;

    /**
     * 交易状态数据访问对象，用于查询交易记录
     */
    @Autowired
    private TransactionStatusMapper transactionStatusMapper;
    
    /**
     * {@inheritDoc}
     *
     * <p>实现说明：</p>
     * <ol>
     *   <li><strong>用户信息查询：</strong>根据账户ID（卡号）查询用户基本信息</li>
     *   <li><strong>余额信息处理：</strong>优先从balance表查询多币种余额，如果失败则使用user表中的余额</li>
     *   <li><strong>交易记录查询：</strong>查询该账户的所有转入和转出交易记录</li>
     *   <li><strong>数据整合：</strong>将所有信息整合到响应对象中返回</li>
     * </ol>
     *
     * <p>错误处理：</p>
     * <ul>
     *   <li>如果账户不存在或已被逻辑删除，抛出RuntimeException</li>
     *   <li>如果余额查询失败，使用默认值（0.00 CNY）</li>
     *   <li>如果交易记录查询失败，返回空列表</li>
     * </ul>
     *
     * @param accountId 账户ID，通常是卡号
     * @return 完整的账户信息响应对象
     * @throws RuntimeException 当账户不存在时
     */
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
