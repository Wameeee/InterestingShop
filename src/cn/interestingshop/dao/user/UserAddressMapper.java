package cn.interestingshop.dao.user;

import java.util.List;

import cn.interestingshop.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

/**
 * UserAddressMapper接口 - MyBatis版本
 */
public interface UserAddressMapper {
    
    /**
     * 保存用户地址信息
     * @param userAddress 用户地址对象
     * @return 影响的行数
     */
    int save(UserAddress userAddress);
    
    /**
     * 更新用户地址信息
     * @param userAddress 用户地址对象
     * @return 影响的行数
     */
    int update(UserAddress userAddress);
    
    /**
     * 根据ID删除用户地址
     * @param id 用户地址ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
    
    /**
     * 查询用户地址列表
     * @param userId 用户ID
     * @return 用户地址列表
     */
    List<UserAddress> selectList(@Param("userId") Integer userId);
    
    /**
     * 根据ID查询用户地址
     * @param id 用户地址ID
     * @return 用户地址对象
     */
    UserAddress selectById(Integer id);
    
    /**
     * 根据用户ID查询默认地址
     * @param userId 用户ID
     * @return 用户默认地址对象
     */
    UserAddress selectDefaultAddress(@Param("userId") Integer userId);
} 