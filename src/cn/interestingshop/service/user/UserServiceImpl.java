package cn.interestingshop.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.interestingshop.dao.user.UserDao;
import cn.interestingshop.dao.user.UserDaoImpl;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public boolean save(User user){
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			count = userDao.save(user);
			sqlSession.commit(); // Service层负责提交事务
		} catch (SQLException e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return count > 0;
	}

	@Override
	public boolean update(User user) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			count = userDao.update(user);
			sqlSession.commit(); // Service层负责提交事务
		} catch (SQLException e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return count > 0;
	}

	@Override
	public boolean deleteById(Integer userId) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			count = userDao.deleteById(userId+"");
			sqlSession.commit(); // Service层负责提交事务
		} catch (SQLException e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback(); // Service层负责回滚事务
			}
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return count > 0;
	}

	@Override
	public User getById(Integer userId, String loginName) {
		Connection connection = null;
		SqlSession sqlSession = null;
		User user = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			user = userDao.selectById(userId, loginName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public List<User> getList(Integer currentPageNo, Integer pageSize) {
		Connection connection = null;
		SqlSession sqlSession = null;
		List<User> userList = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			userList = userDao.selectList(currentPageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return userList;
	}

	@Override
	public int getCount() {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = new UserDaoImpl(connection, sqlSession);
			count = userDao.selectCount();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}
}
