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
import cn.interestingshop.utils.MyBatisUtil;

/**
 * 订单详情Dao实现
 */
public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao {

    private OrderMapper orderMapper;

    public OrderInfoDaoImpl(Connection connection) {
        super(connection);
        // 获取MyBatis的OrderMapper接口实现
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
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
            MyBatisUtil.commit();
            return orderInfo.getId();
        } catch (Exception e) {
            MyBatisUtil.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
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
        } finally {
            MyBatisUtil.closeSqlSession();
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
            MyBatisUtil.commit();
        } catch (Exception e) {
            MyBatisUtil.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    @Override
    public OrderInfo createEntityByResultSet(ResultSet rs) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(rs.getInt("id"));
        orderInfo.setGoodsId(rs.getInt("goodsId"));
        orderInfo.setOrderId(rs.getInt("orderId"));
        orderInfo.setQuantity(rs.getInt("quantity"));
        orderInfo.setCost(rs.getFloat("cost"));
        return orderInfo;
    }
    
    /**
     * 根据参数统计订单详情数量
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public Integer selectCount(OrderInfoParam params) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("select count(*) from t_order_info where 1=1");
        if (EmptyUtils.isNotEmpty(params.getOrderId())) {
            sql.append(" and orderId = ?");
            paramsList.add(params.getOrderId());
        }
        if (EmptyUtils.isNotEmpty(params.getGoodsId())) {
            sql.append(" and goodsId = ?");
            paramsList.add(params.getGoodsId());
        }
        
        ResultSet rs = this.executeQuery(sql.toString(), paramsList.toArray());
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
            this.closeResource(rs);
        }
        return 0;
    }
    
    /**
     * 根据订单ID查询订单详情列表
     * @param baseOrderId
     * @return
     * @throws Exception
     */
    @Override
    public List<OrderInfo> selectList(Integer baseOrderId) throws Exception {
        return selectOrderInfoListByOrderId(baseOrderId);
    }
    
    /**
     * 根据ID查询订单详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public OrderInfo selectById(Integer id) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("select * from t_order_info where id = ?");
        paramsList.add(id);
        
        ResultSet rs = this.executeQuery(sql.toString(), paramsList.toArray());
        try {
            if (rs.next()) {
                return createEntityByResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
            this.closeResource(rs);
        }
        return null;
    }
    
    /**
     * 删除订单详情
     * @param detail
     * @throws Exception
     */
    @Override
    public void deleteById(OrderInfo detail) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("delete from t_order_info where id = ?");
        paramsList.add(detail.getId());
        
        this.executeUpdate(sql.toString(), paramsList.toArray());
    }
}
