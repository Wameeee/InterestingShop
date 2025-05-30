package cn.interestingshop.dao.user;

import java.util.List;

import cn.interestingshop.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * User数据访问接口 - MyBatis版本
 */
public interface UserMapper {
    
    /**
     * 保存用户信息
     * @param user 用户对象
     * @return 影响的行数
     */
    int save(User user);
    
    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 影响的行数
     */
    int update(User user);
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
    
    /**
     * 分页查询用户列表
     * @param offset 起始位置
     * @param limit 每页条数
     * @return 用户列表
     */
    List<User> selectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
    
    /**
     * 查询用户总数
     * @return 用户总数
     */
    int selectCount();
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User selectById(Integer id);
    
    /**
     * 根据账号查询用户
     * @param account 账号
     * @return 用户对象
     */
    User selectByAccount(String account);
    
    /**
     * 根据ID和账号查询用户
     * @param id 用户ID
     * @param account 账号
     * @return 用户对象
     */
    User selectByIdAndAccount(@Param("id") Integer id, @Param("account") String account);
} 