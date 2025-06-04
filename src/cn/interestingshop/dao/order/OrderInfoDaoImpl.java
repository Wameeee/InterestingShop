package cn.interestingshop.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.param.OrderInfoParam;
import cn.interestingshop.utils.EmptyUtils;

/**
 * 订单详情Dao实现
 */
public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao {

    private OrderMapper orderMapper;
    private SqlSession sqlSession;

    public OrderInfoDaoImpl(Connection connection, SqlSession sqlSession) {
        super(connection);
        this.sqlSession = sqlSession;
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    /**
     * 保存订单详情
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @Override
    public Integer save(OrderInfo orderInfo) throws Exception {
        try {
            int result = orderMapper.saveOrderInfo(orderInfo);
            return orderInfo.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public List<OrderInfo> selectOrderInfoListByOrderId(Integer orderId) throws Exception {
        try {
            return orderMapper.selectOrderInfoListByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据订单ID删除订单详情
     * @param orderId
     * @throws Exception
     */
    @Override
    public void deleteByOrderId(Integer orderId) throws Exception {
        try {
            orderMapper.deleteOrderInfoByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteById(OrderInfo detail) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public OrderInfo selectById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrderInfo> selectList(Integer baseOrderId) throws Exception {
        try {
            return orderMapper.selectOrderInfoListByOrderId(baseOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Integer selectCount(OrderInfoParam params) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrderInfo createEntityByResultSet(ResultSet rs) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(rs.getInt("id"));
        orderInfo.setGoodsId(rs.getInt("goodsId"));
        orderInfo.setBaseOrderId(rs.getInt("baseOrderId"));
        orderInfo.setBuyNum(rs.getInt("buyNum"));
        orderInfo.setAmount(rs.getFloat("amount"));
        return orderInfo;
    }
}
