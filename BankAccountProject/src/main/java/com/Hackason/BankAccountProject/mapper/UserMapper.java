package com.Hackason.BankAccountProject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Hackason.BankAccountProject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层接口
 *
 * <p>提供用户信息的数据库访问操作，继承自MyBatis-Plus的BaseMapper，
 * 具备基本的CRUD操作能力，同时提供自定义的查询方法。</p>
 *
 * <p>该接口支持逻辑删除，所有查询操作都会自动过滤已删除的记录。</p>
 *
 * @author Hackason Team
 * @version 1.0
 * @since 2024-01-01
 * @see User
 * @see BaseMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据卡号查询用户信息
     *
     * <p>通过银行卡号查询对应的用户信息，自动过滤已删除的用户记录。
     * 该方法主要用于账户信息查询场景。</p>
     *
     * @param cardId 银行卡号，不能为空
     * @return 用户信息对象，如果未找到则返回null
     */
    @Select("SELECT * FROM user WHERE card_id = #{cardId} AND is_delete = 0")
    User findByCardId(@Param("cardId") String cardId);

    /**
     * 根据用户名查询用户信息
     *
     * <p>通过用户名查询对应的用户信息，自动过滤已删除的用户记录。
     * 该方法主要用于用户登录和身份验证场景。</p>
     *
     * @param username 用户名，不能为空
     * @return 用户信息对象，如果未找到则返回null
     */
    @Select("SELECT * FROM user WHERE user = #{username} AND is_delete = 0")
    User findByUsername(@Param("username") String username);
}
