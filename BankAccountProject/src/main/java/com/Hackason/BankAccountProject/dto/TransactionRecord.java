package com.Hackason.BankAccountProject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易记录数据传输对象
 *
 * <p>用于封装银行交易记录的详细信息，包含交易的完整数据。
 * 该DTO用于在账户查询接口中返回交易历史记录。</p>
 *
 * <p>交易记录包含以下信息：</p>
 * <ul>
 *   <li>交易基本信息：交易ID、交易类型、交易状态</li>
 *   <li>交易金额信息：金额、币种类型</li>
 *   <li>交易对象信息：对方账户</li>
 *   <li>交易时间和描述信息</li>
 * </ul>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 */
@Data
public class TransactionRecord {

    /**
     * 交易唯一标识符
     *
     * <p>每笔交易的唯一ID，用于交易追踪和查询。</p>
     */
    private String transactionId;

    /**
     * 交易类型
     *
     * <p>表示交易的方向，可能的值：</p>
     * <ul>
     *   <li>"转入" - 资金流入当前账户</li>
     *   <li>"转出" - 资金从当前账户流出</li>
     * </ul>
     */
    private String transactionType;

    /**
     * 对方账户号
     *
     * <p>交易对手方的账户号码。</p>
     */
    private String counterpartyAccount;

    /**
     * 交易金额
     *
     * <p>本次交易的金额，使用BigDecimal确保精度。</p>
     */
    private BigDecimal amount;

    /**
     * 币种类型
     *
     * <p>交易使用的货币类型，如CNY、USD等。</p>
     */
    private String currencyType;

    /**
     * 交易状态
     *
     * <p>表示交易的当前状态：PENDING、SUCCESS、FAILED、CANCELLED。</p>
     */
    private String status;

    /**
     * 交易时间
     *
     * <p>交易发生的时间戳。</p>
     */
    private LocalDateTime transactionTime;

    /**
     * 交易描述
     *
     * <p>交易的详细描述信息。</p>
     */
    private String description;
}
