package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE card_id = #{cardId} AND is_delete = 0")
    User findByCardId(@Param("cardId") String cardId);

    @Select("SELECT * FROM user WHERE user = #{username} AND is_delete = 0")
    User findByUsername(@Param("username") String username);
}
