package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.TransactionStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 交易状态数据访问层接口
 *
 * <p>提供交易记录的数据库访问操作，继承自MyBatis-Plus的BaseMapper，
 * 支持交易记录的查询、统计和管理功能。</p>
 *
 * <p>该接口主要用于：</p>
 * <ul>
 *   <li>查询指定账户的所有交易记录（转入和转出）</li>
 *   <li>根据用户名查询交易历史</li>
 *   <li>支持交易状态的更新和管理</li>
 *   <li>提供交易记录的时间排序功能</li>
 * </ul>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 * @see TransactionStatus
 * @see BaseMapper
 */
@Mapper
public interface TransactionStatusMapper extends BaseMapper<TransactionStatus> {

    /**
     * 根据账户号查询所有相关的交易记录
     *
     * <p>查询指定账户作为转出方或转入方的所有交易记录，
     * 结果按创建时间倒序排列，最新的交易记录排在前面。</p>
     *
     * <p>该方法会同时查询：</p>
     * <ul>
     *   <li>该账户作为转出方的交易记录</li>
     *   <li>该账户作为转入方的交易记录</li>
     * </ul>
     *
     * @param account 账户号，可以是卡号或其他账户标识符
     * @return 交易记录列表，按创建时间倒序排列，如果没有交易记录则返回空列表
     */
    @Select("SELECT * FROM transaction_status WHERE from_account = #{account} OR to_account = #{account} ORDER BY created_at DESC")
    List<TransactionStatus> findByAccount(@Param("account") String account);

    /**
     * 根据用户名查询交易记录
     *
     * <p>查询指定用户的所有交易记录，结果按创建时间倒序排列。
     * 该方法主要用于用户个人交易历史查询。</p>
     *
     * @param username 用户名，对应transaction_status表中的username字段
     * @return 交易记录列表，按创建时间倒序排列，如果没有交易记录则返回空列表
     */
    @Select("SELECT * FROM transaction_status WHERE username = #{username} ORDER BY created_at DESC")
    List<TransactionStatus> findByUsername(@Param("username") String username);
}
