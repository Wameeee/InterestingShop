package cn.interestingshop.dao.order;

import java.util.List;

import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

/**
 * OrderMapper接口 - MyBatis版本
 */
public interface OrderMapper {
    
    /**
     * 保存订单基本信息
     * @param baseOrder 订单基本信息对象
     * @return 影响的行数
     */
    int saveBaseOrder(BaseOrder baseOrder);
    
    /**
     * 保存订单详情信息
     * @param orderInfo 订单详情信息对象
     * @return 影响的行数
     */
    int saveOrderInfo(OrderInfo orderInfo);
    
    /**
     * 更新订单状态
     * @param id 订单ID
     * @param status 订单状态
     * @return 影响的行数
     */
    int updateOrderStatus(@Param("id") Integer id, @Param("status") Integer status);
    
    /**
     * 根据ID删除订单
     * @param id 订单ID
     * @return 影响的行数
     */
    int deleteBaseOrderById(Integer id);
    
    /**
     * 根据订单ID删除订单详情
     * @param orderId 订单ID
     * @return 影响的行数
     */
    int deleteOrderInfoByOrderId(Integer orderId);
    
    /**
     * 分页查询用户订单列表
     * @param offset 起始位置
     * @param limit 每页条数
     * @param userId 用户ID
     * @return 订单列表
     */
    List<BaseOrder> selectBaseOrderList(@Param("offset") Integer offset, 
                                      @Param("limit") Integer limit,
                                      @Param("userId") Integer userId);
    
    /**
     * 根据条件查询订单总数
     * @param userId 用户ID
     * @return 订单总数
     */
    int selectBaseOrderCount(@Param("userId") Integer userId);
    
    /**
     * 根据ID查询订单
     * @param id 订单ID
     * @return 订单对象
     */
    BaseOrder selectBaseOrderById(Integer id);
    
    /**
     * 根据订单ID查询订单详情列表
     * @param orderId 订单ID
     * @return 订单详情列表
     */
    List<OrderInfo> selectOrderInfoListByOrderId(Integer orderId);
} 