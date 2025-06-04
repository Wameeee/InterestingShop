package cn.interestingshop.service.notice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.notice.NoticeDao;
import cn.interestingshop.dao.notice.NoticeDaoImpl;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;

/**
 *
 */
public class NoticeServiceImpl implements NoticeService {

	public void deleteById(String id) {// 删除新闻
		Connection connection = null;
		SqlSession sqlSession = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			noticeDao.deleteById(Integer.parseInt(id));
			sqlSession.commit();
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
	}

	public Notice getById(String id) {// 根据ID获取新闻
		Notice notice = null;
		Connection connection = null;
		SqlSession sqlSession = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			notice = noticeDao.selectById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
			DataSourceUtil.closeConnection(connection);
		}
		return notice;
	}

	public void save(Notice notice) {// 保存新闻
		Connection connection = null;
		SqlSession sqlSession = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			noticeDao.save(notice);
			sqlSession.commit();
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
	}

	public void update(Notice notice) {// 更新留言
		Connection connection = null;
		SqlSession sqlSession = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			noticeDao.update(notice);
			sqlSession.commit();
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
	}

	public List<Notice> queryNoticePageList(NoticeParams param) throws SQLException {
		List<Notice> noticeList = new ArrayList<Notice>();
		Connection connection = null;
		SqlSession sqlSession = null;
		NoticeDao noticeDao = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			noticeDao = new NoticeDaoImpl(connection, sqlSession);
			noticeList = noticeDao.selectList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
			System.out.println(connection.isClosed());
			DataSourceUtil.closeConnection(connection);
		}
		return noticeList;
	}

	@Override
	public List<Notice> getList(NoticeParams param) {
		List<Notice> noticeList = new ArrayList<Notice>();
		Connection connection = null;
		SqlSession sqlSession = null;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			noticeList = noticeDao.selectList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
			DataSourceUtil.closeConnection(connection);
		}
		return noticeList;
	}

	@Override
	public Integer getCount(NoticeParams param) {
		Connection connection = null;
		SqlSession sqlSession = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			sqlSession = MyBatisUtil.openSession();
			NoticeDao noticeDao = new NoticeDaoImpl(connection, sqlSession);
			count = noticeDao.selectCount(param);
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

}
