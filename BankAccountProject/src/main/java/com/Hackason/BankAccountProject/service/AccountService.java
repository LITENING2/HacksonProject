package com.Hackason.BankAccountProject.service;

import com.Hackason.BankAccountProject.dto.AccountInfoResponse;

/**
 * 银行账户服务接口
 *
 * <p>定义了银行账户相关的业务逻辑接口，提供账户信息查询、余额查询、
 * 交易记录查询等核心业务功能。</p>
 *
 * <p>该接口遵循面向接口编程的设计原则，便于后续扩展和测试。</p>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 */
public interface AccountService {

    /**
     * 根据账户ID获取完整的账户信息
     *
     * <p>该方法会查询并整合以下信息：</p>
     * <ul>
     *   <li>用户基本信息（用户名、昵称、卡号等）</li>
     *   <li>账户余额信息（支持多币种）</li>
     *   <li>交易历史记录（转入和转出记录）</li>
     * </ul>
     *
     * <p>查询逻辑：</p>
     * <ol>
     *   <li>首先根据账户ID从用户表查询基本信息</li>
     *   <li>然后从余额表查询多币种余额信息</li>
     *   <li>最后从交易表查询相关的交易记录</li>
     * </ol>
     *
     * @param accountId 账户ID，通常是卡号或其他唯一标识符，不能为空
     * @return 包含完整账户信息的响应对象，包括基本信息、余额和交易记录
     * @throws RuntimeException 当账户不存在或已被删除时抛出
     * @throws IllegalArgumentException 当账户ID为空或格式不正确时抛出
     */
    AccountInfoResponse getAccountInfo(String accountId);
}
