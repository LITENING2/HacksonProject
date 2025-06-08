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
 *
 * <p>对应数据库中的user表，存储银行用户的基本信息。
 * 该实体类使用MyBatis-Plus注解进行ORM映射，支持逻辑删除功能。</p>
 *
 * <p>主要包含以下信息：</p>
 * <ul>
 *   <li>用户基本信息：用户名、昵称、性别等</li>
 *   <li>身份信息：证件类型、证件号码</li>
 *   <li>联系方式：电话、邮箱</li>
 *   <li>账户信息：卡号、余额</li>
 *   <li>系统信息：创建时间、更新时间、删除标记</li>
 * </ul>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 */
@Data
@TableName("user")
public class User {

    /**
     * 用户名（主键）
     *
     * <p>作为用户的唯一标识符，同时也是数据库表的主键。
     * 使用INPUT类型表示主键值由程序指定。</p>
     */
    @TableId(value = "user", type = IdType.INPUT)
    private String user;

    /**
     * 用户昵称
     *
     * <p>用户的显示名称，用于界面展示，可以为空。</p>
     */
    private String nickname;

    /**
     * 证件类型
     *
     * <p>用户身份证件的类型标识，对应数据库字段Identity_Type。
     * 通常使用数字代码表示不同的证件类型。</p>
     */
    @TableField("Identity_Type")
    private Integer identityType;

    /**
     * 证件号码
     *
     * <p>用户身份证件的号码，对应数据库字段Identity_Card。
     * 用于身份验证和实名认证。</p>
     */
    @TableField("Identity_Card")
    private String identityCard;

    /**
     * 用户性别
     *
     * <p>用户的性别信息，通常为"男"、"女"或其他值。</p>
     */
    private String gender;

    /**
     * 联系电话
     *
     * <p>用户的手机号码或固定电话，用于联系和验证。</p>
     */
    private String phone;

    /**
     * 电子邮箱
     *
     * <p>用户的邮箱地址，用于通知和找回密码等功能。</p>
     */
    private String email;

    /**
     * 用户头像
     *
     * <p>用户头像图片的URL或路径。</p>
     */
    private String logo;

    /**
     * 登录密码
     *
     * <p>用户的登录密码，通常经过加密处理存储。</p>
     */
    private String password;

    /**
     * 银行卡号
     *
     * <p>用户的银行卡号，作为账户的唯一标识，对应数据库字段card_id。</p>
     */
    @TableField("card_id")
    private String cardId;

    /**
     * 账户余额
     *
     * <p>用户账户的余额信息，以字符串形式存储。
     * 注意：实际应用中建议使用BigDecimal类型处理金额。</p>
     */
    private String balance;

    /**
     * 创建时间
     *
     * <p>用户账户的创建时间，对应数据库字段create_time。</p>
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     *
     * <p>用户信息的最后更新时间，对应数据库字段update_time。</p>
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标记
     *
     * <p>逻辑删除标记，对应数据库字段is_delete。
     * 0表示正常状态，1表示已删除。使用@TableLogic注解支持逻辑删除。</p>
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
}
