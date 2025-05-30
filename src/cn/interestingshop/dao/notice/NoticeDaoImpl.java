package cn.interestingshop.dao.notice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.MyBatisUtil;
import cn.interestingshop.utils.Pager;

/**
 * 公告dao实现类
 */
public class NoticeDaoImpl extends BaseDaoImpl implements NoticeDao {

	private NoticeMapper noticeMapper;

	public NoticeDaoImpl(Connection connection) {
		super(connection);
		// 获取MyBatis的NoticeMapper接口实现
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		this.noticeMapper = sqlSession.getMapper(NoticeMapper.class);
	}

	/**
	 * 根据id获取公告
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Notice selectById(Integer id) throws Exception {
		try {
			return noticeMapper.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyBatisUtil.closeSqlSession();
		}
	}

	/**
	 * 分页获取公告列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Notice> selectList(NoticeParams params) throws Exception {
		try {
			// Get count directly using the mapper instead of calling selectCount
			Integer count = noticeMapper.selectCount(params.getTitle());
			String title = params.getTitle();
			Integer currentPageNo = params.getStartIndex() / params.getPageSize() + 1;
			Integer pageSize = params.getPageSize();
			
			Pager pager = new Pager(count, pageSize, currentPageNo);
			int offset = (pager.getCurrentPage() - 1) * pager.getRowPerPage();
			
			return noticeMapper.selectList(offset, pager.getRowPerPage(), title);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyBatisUtil.closeSqlSession();
		}
	}

	/**
	 * 统计公告数量
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer selectCount(NoticeParams params) throws Exception {
		try {
			return noticeMapper.selectCount(params.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MyBatisUtil.closeSqlSession();
		}
	}

	/**
	 * 添加公告
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@Override
	public void save(Notice notice) throws Exception {
		try {
			noticeMapper.save(notice);
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
	 * 修改公告
	 * @param notice
	 * @throws Exception
	 */
	@Override
	public void update(Notice notice) throws Exception {
		try {
			noticeMapper.update(notice);
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
	 * 根据id删除公告
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteById(Integer id) throws Exception {
		try {
			noticeMapper.deleteById(id);
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
	public Notice createEntityByResultSet(ResultSet rs) throws Exception {
		Notice notice = new Notice();
		notice.setId(rs.getInt("id"));
		notice.setTitle(rs.getString("title"));
		notice.setContent(rs.getString("content"));
		notice.setCreateTime(rs.getDate("createTime"));
		return notice;
	}
}
