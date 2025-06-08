package com.Hackason.BankAccountProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易状态实体类
 */
@Data
@TableName("transaction_status")
public class TransactionStatus {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String currencyType;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
