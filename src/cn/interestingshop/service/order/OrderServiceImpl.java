package cn.interestingshop.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.goods.GoodsDao;
import cn.interestingshop.dao.goods.GoodsDaoImpl;
import cn.interestingshop.dao.order.BaseOrderDao;
import cn.interestingshop.dao.order.BaseOrderDaoImpl;
import cn.interestingshop.dao.order.OrderInfoDao;
import cn.interestingshop.dao.order.OrderInfoDaoImpl;
import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;
import cn.interestingshop.utils.ShopCart;
import cn.interestingshop.utils.ShopGoods;
import cn.interestingshop.utils.StringUtils;

public class OrderServiceImpl implements OrderService {

    /**
     * 结算购物车物品包含以下步骤：
     * 1.生成订单
     * 2.生成订单明细
     * 3.更新商品表，减库存
     * 注意加入事物的控制
     */

    @Override
    public BaseOrder pay(User user, ShopCart shopCart, String address) {
        // TODO Auto-generated method stub
        Connection connection = null;
        SqlSession sqlSession = null;
        BaseOrder baseOrder = new BaseOrder();
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            connection.setAutoCommit(false);
            GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection, sqlSession);
            OrderInfoDao orderInfoDao = new OrderInfoDaoImpl(connection, sqlSession);
            //增加订单
            baseOrder.setUserId(user.getId());
            baseOrder.setAccount(user.getAccount());
            baseOrder.setUserAddress(address);
            baseOrder.setCreateTime(new Date());
            baseOrder.setAmount(shopCart.getAmount());
            baseOrder.setOrderNo(StringUtils.randomUUID());
            baseOrder.setCost(shopCart.getAmount()); // 设置cost字段
            baseOrder.setSerialNumber(StringUtils.randomUUID()); // 设置serialNumber字段
            baseOrder.setPayType(1); // 设置默认支付类型
            baseOrder.setStatus(1); // 设置默认订单状态
            // 不设置addressId字段，因为数据库表中没有这个字段
            orderDao.save(baseOrder);
            //增加订单对应的明细信息
            for (ShopGoods shopGoods : shopCart.getShopGoodsList()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setBaseOrderId(baseOrder.getId());
                orderInfo.setAmount(shopGoods.getAmount());
                orderInfo.setGoods(shopGoods.getGoods());
                orderInfo.setGoodsId(shopGoods.getGoods().getId());
                orderInfo.setBuyNum(shopGoods.getBuyNum());
                orderInfoDao.save(orderInfo);
                //更新商品表的库存
                goodsDao.updateStock(shopGoods.getGoods().getId(), shopGoods.getBuyNum());
                sqlSession.commit();
                connection.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                if (sqlSession != null) {
                    sqlSession.rollback();
                }
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            baseOrder = null;
        } finally {
            try {
                connection.setAutoCommit(true);
                if (sqlSession != null) {
                    sqlSession.close();
                }
                DataSourceUtil.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return baseOrder;
    }

    @Override
    public List<BaseOrder> getBaseList(Integer userId, Integer currentPageNo, Integer pageSize) {
        Connection connection = null;
        SqlSession sqlSession = null;
        List<BaseOrder> orderList = new ArrayList<BaseOrder>();
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection, sqlSession);
            OrderInfoDao orderInfoDao = new OrderInfoDaoImpl(connection, sqlSession);
            orderList = orderDao.selectList(userId, currentPageNo, pageSize);
            for(BaseOrder baseOrder:orderList){
            	baseOrder.setOrderInfoList(orderInfoDao.selectList(baseOrder.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public int count(Integer userId) {
        Connection connection = null;
        SqlSession sqlSession = null;
        Integer count=0;
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection, sqlSession);
            count=orderDao.selectCount(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
        }
        return count;
    }

    /**
     * 调用dao接口：OrderInfoMapper的方法实现
     */
    @Override
    public List<OrderInfo> getOrderInfoList(Integer orderId) {
        Connection connection = null;
        SqlSession sqlSession = null;
        List<OrderInfo> OrderInfoList = new ArrayList<OrderInfo>();
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            OrderInfoDao OrderInfoDao = new OrderInfoDaoImpl(connection, sqlSession);
            OrderInfoList = OrderInfoDao.selectList(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
        }
        return OrderInfoList;
    }
}
