package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.TransactionStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 交易状态数据访问层
 */
@Mapper
public interface TransactionStatusMapper extends BaseMapper<TransactionStatus> {

    @Select("SELECT * FROM transaction_status WHERE from_account = #{account} OR to_account = #{account} ORDER BY created_at DESC")
    List<TransactionStatus> findByAccount(@Param("account") String account);

    @Select("SELECT * FROM transaction_status WHERE username = #{username} ORDER BY created_at DESC")
    List<TransactionStatus> findByUsername(@Param("username") String username);
}
