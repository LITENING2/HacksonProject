package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 余额数据访问层
 */
@Mapper
public interface BalanceMapper extends BaseMapper<Balance> {

    @Select("SELECT * FROM balance WHERE accountId = #{accountId}")
    List<Balance> findByAccountId(@Param("accountId") Long accountId);
}
