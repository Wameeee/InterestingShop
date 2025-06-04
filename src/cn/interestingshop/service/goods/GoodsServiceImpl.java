package cn.interestingshop.service.goods;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.interestingshop.dao.goods.GoodsDao;
import cn.interestingshop.dao.goods.GoodsDaoImpl;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;
import cn.interestingshop.utils.ThreadLocalContext;

/**
 * 商品的业务类
 */
public class GoodsServiceImpl implements GoodsService {
	
	private Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	
	@Override
	public boolean save(Goods goods) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			count = goodsDao.save(goods);
			sqlSession.commit(); // Service层负责提交事务
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
	public boolean update(Goods goods) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			count = goodsDao.update(goods);
			sqlSession.commit(); // Service层负责提交事务
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
	public boolean deleteById(Integer goodsId) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			count = goodsDao.deleteById(goodsId);
			sqlSession.commit(); // Service层负责提交事务
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
	public Goods getById(Integer goodsId) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Goods goods = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			goods = goodsDao.selectById(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return goods;
	}

	@Override
	public List<Goods> getList(Integer currentPageNo, Integer pageSize, String goodsName, Integer categoryId) {
		// 默认使用1级分类查询
		return getListByLevel(currentPageNo, pageSize, goodsName, categoryId, 1);
	}
	
	@Override
	public List<Goods> getListByLevel(Integer currentPageNo, Integer pageSize, String goodsName, Integer categoryId, Integer level) {
		Connection connection = null;
		SqlSession sqlSession = null;
		List<Goods> productList = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			
			// 设置分类级别到ThreadLocal
			ThreadLocalContext.setLevel(level);
			
			productList = goodsDao.selectList(currentPageNo, pageSize, goodsName, categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 清除ThreadLocal中的数据，防止内存泄漏
			ThreadLocalContext.clear();
			
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return productList;
	}

	@Override
	public int getCount(String goodsName, Integer categoryId) {
		// 默认使用1级分类查询
		return getCountByLevel(goodsName, categoryId, 1);
	}
	
	@Override
	public int getCountByLevel(String goodsName, Integer categoryId, Integer level) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			
			// 设置分类级别到ThreadLocal
			ThreadLocalContext.setLevel(level);
			
			count = goodsDao.selectCount(goodsName, categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 清除ThreadLocal中的数据，防止内存泄漏
			ThreadLocalContext.clear();
			
			if (sqlSession != null) {
				sqlSession.close(); // Service层负责关闭SqlSession
			}
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}

	@Override
	public boolean updateStock(Integer goodsId, Integer stock) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			GoodsDao goodsDao = new GoodsDaoImpl(connection, sqlSession);
			count = goodsDao.updateStock(goodsId, stock);
			sqlSession.commit(); // Service层负责提交事务
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
}
