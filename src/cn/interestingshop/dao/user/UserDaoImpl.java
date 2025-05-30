package cn.interestingshop.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.MyBatisUtil;
import cn.interestingshop.utils.Pager;

/**
 * 用户dao
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private UserMapper userMapper;

    public UserDaoImpl(Connection connection) {
        super(connection);
        // 获取MyBatis的UserMapper接口实现
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 保存用户
     *
     * @param user
     * @throws java.sql.SQLException
     */
    public int save(User user) {
        try {
            int result = userMapper.save(user);
            MyBatisUtil.commit(); // 提交事务
            return user.getId();
        } catch (Exception e) {
            MyBatisUtil.rollback(); // 回滚事务
            e.printStackTrace();
            return 0;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    //更新用户信息
    public int update(User user) {
        try {
            int result = userMapper.update(user);
            MyBatisUtil.commit(); // 提交事务
            return result;
        } catch (Exception e) {
            MyBatisUtil.rollback(); // 回滚事务
            e.printStackTrace();
            return 0;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            int result = userMapper.deleteById(Integer.parseInt(id));
            MyBatisUtil.commit(); // 提交事务
            return result;
        } catch (Exception e) {
            MyBatisUtil.rollback(); // 回滚事务
            e.printStackTrace();
            return 0;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    @Override
    public List<User> selectList(Integer currentPageNo, Integer pageSize) throws Exception {
        try {
            int total = selectCount();
            Pager pager = new Pager(total, pageSize, currentPageNo);
            int offset = (pager.getCurrentPage() - 1) * pager.getRowPerPage();
            
            List<User> userList = userMapper.selectList(offset, pager.getRowPerPage());
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }
    
    public Integer selectCount() throws Exception {
        try {
            return userMapper.selectCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public User selectById(Integer id, String account) throws Exception {
        try {
            if (EmptyUtils.isNotEmpty(id) && EmptyUtils.isEmpty(account)) {
                return userMapper.selectById(id);
            } else if (EmptyUtils.isEmpty(id) && EmptyUtils.isNotEmpty(account)) {
                return userMapper.selectByAccount(account);
            } else {
                return userMapper.selectByIdAndAccount(id, account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            MyBatisUtil.closeSqlSession();
        }
    }

    @Override
    public User createEntityByResultSet(ResultSet rs) throws Exception {
        User user = new User();
        user.setAccount(rs.getString("account"));
        user.setNickName(rs.getString("nickName"));
        user.setPassword(rs.getString("password"));
        user.setGender(rs.getInt("gender"));
        user.setIdCardNo(rs.getString("idCardNo"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setType(rs.getInt("type"));
        user.setId(rs.getInt("id"));
        return user;
    }
}
