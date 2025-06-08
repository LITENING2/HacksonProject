package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 余额数据访问层接口
 *
 * <p>提供账户余额信息的数据库访问操作，继承自MyBatis-Plus的BaseMapper，
 * 支持多币种余额的查询和管理。</p>
 *
 * <p>该接口主要用于：</p>
 * <ul>
 *   <li>查询指定账户的所有币种余额</li>
 *   <li>支持余额的增删改查操作</li>
 *   <li>提供多币种账户管理功能</li>
 * </ul>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 * @see Balance
 * @see BaseMapper
 */
@Mapper
public interface BalanceMapper extends BaseMapper<Balance> {

    /**
     * 根据账户ID查询所有币种的余额信息
     *
     * <p>查询指定账户下的所有币种余额记录，支持多币种账户管理。
     * 返回的列表包含该账户的所有币种余额信息。</p>
     *
     * @param accountId 账户ID，对应balance表中的accountId字段
     * @return 余额信息列表，如果账户没有余额记录则返回空列表
     */
    @Select("SELECT * FROM balance WHERE accountId = #{accountId}")
    List<Balance> findByAccountId(@Param("accountId") Long accountId);
}
