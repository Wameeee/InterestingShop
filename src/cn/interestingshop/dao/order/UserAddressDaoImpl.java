package cn.interestingshop.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.dao.user.UserAddressMapper;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.MyBatisUtil;

/**
 * 用户地址Dao实现
 */
public class UserAddressDaoImpl extends BaseDaoImpl implements UserAddressDao {

    private UserAddressMapper userAddressMapper;

    public UserAddressDaoImpl(Connection connection) {
        super(connection);
        // 获取MyBatis的UserAddressMapper接口实现
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        this.userAddressMapper = sqlSession.getMapper(UserAddressMapper.class);
    }

    /**
     * 根据ID查询用户地址
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserAddress selectById(Integer id) throws Exception {
        try {
            return userAddressMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    /**
     * 查询用户地址列表
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<UserAddress> selectList(Integer userId) throws Exception {
        try {
            return userAddressMapper.selectList(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    /**
     * 保存用户地址
     * @param userAddress
     * @return
     * @throws Exception
     */
    @Override
    public Integer save(UserAddress userAddress) throws Exception {
        try {
            // 如果设置为默认地址，需要将其他地址设置为非默认
            if (userAddress.getIsDefault() == 1) {
                resetDefaultAddress(userAddress.getUserId());
            }
            userAddress.setCreateTime(new Date());
            int result = userAddressMapper.save(userAddress);
            MyBatisUtil.commit();
            return userAddress.getId();
        } catch (Exception e) {
            MyBatisUtil.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    /**
     * 更新用户地址
     * @param userAddress
     * @throws Exception
     */
    @Override
    public void update(UserAddress userAddress) throws Exception {
        try {
            // 如果设置为默认地址，需要将其他地址设置为非默认
            if (userAddress.getIsDefault() == 1) {
                resetDefaultAddress(userAddress.getUserId());
            }
            userAddressMapper.update(userAddress);
            MyBatisUtil.commit();
        } catch (Exception e) {
            MyBatisUtil.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    /**
     * 删除用户地址
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            userAddressMapper.deleteById(id);
            MyBatisUtil.commit();
        } catch (Exception e) {
            MyBatisUtil.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    /**
     * 重置用户的默认地址
     * @param userId
     * @throws Exception
     */
    private void resetDefaultAddress(Integer userId) throws Exception {
        List<UserAddress> addresses = userAddressMapper.selectList(userId);
        for (UserAddress address : addresses) {
            if (address.getIsDefault() == 1) {
                address.setIsDefault(0);
                userAddressMapper.update(address);
            }
        }
    }

    @Override
    public UserAddress createEntityByResultSet(ResultSet rs) throws Exception {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(rs.getInt("id"));
        userAddress.setUserId(rs.getInt("userId"));
        userAddress.setAddress(rs.getString("address"));
        userAddress.setCreateTime(rs.getDate("createTime"));
        userAddress.setRemark(rs.getString("remark"));
        userAddress.setName(rs.getString("name"));
        userAddress.setPhone(rs.getString("phone"));
        userAddress.setIsDefault(rs.getInt("isDefault"));
        return userAddress;
    }

    @Override
    public List<UserAddress> selectList(UserAddressParam params) {
        List<Object> paramsList = new ArrayList<Object>();   
        List<UserAddress> userAddresseList = new ArrayList<UserAddress>();
        StringBuffer sql = new StringBuffer("  select id,userId,address,createTime,isDefault,remark,name,phone from t_user_address where 1=1 ");
        if(EmptyUtils.isNotEmpty(params.getUserId())){
            sql.append(" and userId = ? ");
            paramsList.add(params.getUserId());
        }
        if(EmptyUtils.isNotEmpty(params.getAddress())){
            sql.append(" and address like ? ");
            paramsList.add("%"+params.getAddress()+"%");
        }
        if(EmptyUtils.isNotEmpty(params.getSort())){
            sql.append(" order by " + params.getSort()+" ");
        }
        if(params.isPage()){
            sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
        }
        ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
        try {
            while (resultSet.next()) {
                UserAddress userAddress = this.createEntityByResultSet(resultSet);
                userAddresseList.add(userAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
            this.closeResource(resultSet);
        }
        return userAddresseList;
    }
}
