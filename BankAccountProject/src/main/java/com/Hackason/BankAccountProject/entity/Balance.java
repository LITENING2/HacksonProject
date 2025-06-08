package com.Hackason.BankAccountProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 余额实体类
 */
@Data
@TableName("balance")
public class Balance {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
}
