package com.Hackason.BankAccountProject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户信息响应数据传输对象
 *
 * <p>用于封装账户查询接口的响应数据，包含账户的完整信息。
 * 该DTO整合了用户基本信息、多币种余额信息和交易记录。</p>
 *
 * <p>响应数据结构：</p>
 * <ul>
 *   <li>账户基本信息：账户ID、用户名、昵称、卡号</li>
 *   <li>余额信息：支持多币种的余额列表</li>
 *   <li>交易记录：包含转入和转出的交易历史</li>
 * </ul>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 * @see TransactionRecord
 */
@Data
public class AccountInfoResponse {

    /**
     * 账户ID
     *
     * <p>账户的唯一标识符，通常是银行卡号或其他账户编号。</p>
     */
    private String accountId;

    /**
     * 用户名
     *
     * <p>账户所属用户的用户名，用于用户身份识别。</p>
     */
    private String username;

    /**
     * 用户昵称
     *
     * <p>用户的显示名称，用于界面友好展示。</p>
     */
    private String nickname;

    /**
     * 银行卡号
     *
     * <p>与账户关联的银行卡号，可能与accountId相同。</p>
     */
    private String cardId;

    /**
     * 余额信息列表
     *
     * <p>包含该账户所有币种的余额信息，支持多币种账户管理。</p>
     *
     * @see BalanceInfo
     */
    private List<BalanceInfo> balances;

    /**
     * 交易记录列表
     *
     * <p>包含该账户的所有交易记录，包括转入和转出交易。</p>
     *
     * @see TransactionRecord
     */
    private List<TransactionRecord> transactions;

    /**
     * 余额信息内部类
     *
     * <p>用于表示特定币种的余额信息，包含金额和币种类型。</p>
     *
     * @author Hackason Team
     * @version 1.0
     * @since 2024-01-01
     */
    @Data
    public static class BalanceInfo {

        /**
         * 余额金额
         *
         * <p>该币种的账户余额，使用BigDecimal确保精度。</p>
         */
        private BigDecimal amount;

        /**
         * 币种类型
         *
         * <p>余额对应的币种，如CNY（人民币）、USD（美元）等。</p>
         */
        private String currency;
    }
}
