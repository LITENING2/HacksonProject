package com.Hackason.BankAccountProject.controller;

import com.Hackason.BankAccountProject.common.Result;
import com.Hackason.BankAccountProject.dto.AccountInfoResponse;
import com.Hackason.BankAccountProject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户查询控制器
 */
@RestController
@RequestMapping("/accounts")
@Tag(name = "账户管理", description = "银行账户信息查询相关接口")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @GetMapping("/{accountId}")
    @Operation(summary = "获取账户信息", description = "根据账户ID获取账户信息，包括余额和交易记录")
    public Result<AccountInfoResponse> getAccountInfo(
            @Parameter(description = "账户ID", required = true)
            @PathVariable String accountId) {

        try {
            AccountInfoResponse accountInfo = accountService.getAccountInfo(accountId);
            return Result.success(accountInfo);
        } catch (RuntimeException e) {
            return Result.error(404, "账户不存在: " + e.getMessage());
        } catch (Exception e) {
            return Result.error("服务器内部错误: " + e.getMessage());
        }
    }
}
