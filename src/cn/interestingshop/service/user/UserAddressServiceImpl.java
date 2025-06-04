package cn.interestingshop.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.order.UserAddressDao;
import cn.interestingshop.dao.order.UserAddressDaoImpl;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddressServiceImpl implements UserAddressService {
    /**
     * 查询用户地址列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<UserAddress> getList(Integer id) throws Exception{
        Connection connection = null;
        SqlSession sqlSession = null;
        List<UserAddress> userAddressList = null;
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection, sqlSession);
            UserAddressParam params = new UserAddressParam();
            params.setUserId(id);
            userAddressList = userAddressDao.selectList(params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
        }
        return userAddressList;
    }
    /**
     * 添加用户地址
     *
     * @param id
     * @param address
     * @return
     */
    @Override
    public Integer save(Integer id, String address, String remark) {
        Connection connection = null;
        SqlSession sqlSession = null;
        Integer userAddressId = null;
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection, sqlSession);
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(id);
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddressId = userAddressDao.save(userAddress);
            sqlSession.commit();
        } catch (SQLException e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
        }
        return userAddressId;
    }

    @Override
    public UserAddress getById(Integer id) {
        Connection connection = null;
        SqlSession sqlSession = null;
        UserAddress userAddress = null;
        try {
            connection = DataSourceUtil.openConnection();
            sqlSession = MyBatisUtil.openSession();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection, sqlSession);
            userAddress = (UserAddress) userAddressDao.selectById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            DataSourceUtil.closeConnection(connection);
            return userAddress;
        }
    }
}
