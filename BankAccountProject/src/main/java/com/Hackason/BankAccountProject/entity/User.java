package com.Hackason.BankAccountProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("user")
public class User {

    @TableId(value = "user", type = IdType.INPUT)
    private String user;
    private String nickname;

    @TableField("Identity_Type")
    private Integer identityType;

    @TableField("Identity_Card")
    private String identityCard;

    private String gender;
    private String phone;
    private String email;
    private String logo;
    private String password;

    @TableField("card_id")
    private String cardId;

    private String balance;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
}
