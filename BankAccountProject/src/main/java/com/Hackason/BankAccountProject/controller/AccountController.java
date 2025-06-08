package com.Hackason.BankAccountProject.controller;

import com.Hackason.BankAccountProject.dto.AccountInfoResponse;
import com.Hackason.BankAccountProject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 银行账户查询控制器
 *
 * <p>提供银行账户信息查询相关的RESTful API接口，包括：</p>
 * <ul>
 *   <li>根据账户ID查询账户基本信息</li>
 *   <li>查询账户余额信息（支持多币种）</li>
 *   <li>查询账户交易记录</li>
 * </ul>
 *
 * <p>所有接口都遵循RESTful设计规范，返回统一的JSON格式响应。</p>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/accounts")
@Tag(name = "账户管理", description = "银行账户信息查询相关接口")
public class AccountController {

    /**
     * 账户服务接口，用于处理账户相关的业务逻辑
     */
    @Autowired
    private AccountService accountService;

    /**
     * 根据账户ID获取完整的账户信息
     *
     * <p>该接口提供账户的完整信息查询功能，包括：</p>
     * <ul>
     *   <li>账户基本信息：账户ID、用户名、昵称、卡号</li>
     *   <li>余额信息：支持多币种余额查询</li>
     *   <li>交易记录：包括转入和转出的所有交易记录</li>
     * </ul>
     *
     * <p><strong>请求示例：</strong></p>
     * <pre>
     * GET /accounts/123456789
     * </pre>
     *
     * <p><strong>响应示例：</strong></p>
     * <pre>
     * {
     *   "accountId": "123456789",
     *   "username": "testuser",
     *   "nickname": "测试用户",
     *   "cardId": "123456789",
     *   "balances": [
     *     {
     *       "amount": 1000.00,
     *       "currency": "CNY"
     *     }
     *   ],
     *   "transactions": [...]
     * }
     * </pre>
     *
     * @param accountId 账户ID，可以是卡号或其他唯一标识符
     * @return 包含账户完整信息的响应结果，如果账户不存在则返回404错误
     * @throws RuntimeException 当账户不存在时抛出
     * @throws Exception 当系统内部错误时抛出
     */
    @GetMapping("/{accountId}")
    @Operation(summary = "获取账户信息", description = "根据账户ID获取账户信息，包括余额和交易记录")
    public ResponseEntity<AccountInfoResponse> getAccountInfo(
            @Parameter(description = "账户ID", required = true)
            @PathVariable String accountId) {

        try {
            AccountInfoResponse accountInfo = accountService.getAccountInfo(accountId);
            return ResponseEntity.ok(accountInfo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
