package com.Hackason.BankAccountProject.service;

import com.Hackason.BankAccountProject.dto.AccountInfoResponse;

/**
 * 账户服务接口
 */
public interface AccountService {
    AccountInfoResponse getAccountInfo(String accountId);
}
