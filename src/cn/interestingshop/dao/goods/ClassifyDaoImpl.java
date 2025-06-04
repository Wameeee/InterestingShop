package cn.interestingshop.dao.goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;

/**
 * 商品分类dao实现类
 */
public class ClassifyDaoImpl extends BaseDaoImpl implements ClassifyDao {

	private ClassifyMapper classifyMapper;
	private SqlSession sqlSession;

	public ClassifyDaoImpl(Connection connection, SqlSession sqlSession) {
		super(connection);
		this.sqlSession = sqlSession;
		this.classifyMapper = sqlSession.getMapper(ClassifyMapper.class);
	}

	/**
	 * 根据id获取商品分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Classify selectById(Integer id) throws Exception {
		try {
			return classifyMapper.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取所有商品分类列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Classify> selectAll() throws Exception {
		try {
			return classifyMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取分页商品分类列表
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Classify> selectList(Integer currentPageNo, Integer pageSize) throws Exception {
		try {
			Integer count = selectCount();
			Pager pager = new Pager(count, pageSize, currentPageNo);

			// 修复：确保当前页码至少为1
			int currentPage = Math.max(pager.getCurrentPage(), 1);
			int offset = (currentPage - 1) * pager.getRowPerPage();

			return classifyMapper.selectList(offset, pager.getRowPerPage());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 统计商品分类数量
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer selectCount() throws Exception {
		try {
			return classifyMapper.selectCount();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 添加商品分类
	 * @param classify
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer save(Classify classify) throws Exception {
		try {
			int result = classifyMapper.save(classify);
			return classify.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改商品分类
	 * @param classify
	 * @throws Exception
	 */
	@Override
	public void update(Classify classify) throws Exception {
		try {
			classifyMapper.update(classify);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 根据id删除商品分类
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteById(Integer id) throws Exception {
		try {
			classifyMapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Classify createEntityByResultSet(ResultSet rs) throws Exception {
		Classify classify = new Classify();
		classify.setId(rs.getInt("id"));
		classify.setName(rs.getString("classifyName"));
		classify.setParentId(rs.getInt("parentId"));
		classify.setType(rs.getInt("type"));
		classify.setIconClass(rs.getString("icon"));
		return classify;
	}
	
	/**
	 * 根据参数查询商品分类数目
	 * @param param
	 * @return
	 */
	@Override
	public Integer selectCount(ClassifyParam param) {
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select count(*) from t_classify where 1=1");
		if (EmptyUtils.isNotEmpty(param.getParentId())) {
			sql.append(" and parentId = ?");
			paramsList.add(param.getParentId());
		}
		if (EmptyUtils.isNotEmpty(param.getType())) {
			sql.append(" and type = ?");
			paramsList.add(param.getType());
		}
		if (EmptyUtils.isNotEmpty(param.getClassifyName())) {
			sql.append(" and classifyName like ?");
			paramsList.add("%" + param.getClassifyName() + "%");
		}
		
		ResultSet rs = this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
			this.closeResource(rs);
		}
		return 0;
	}
	
	/**
	 * 根据条件查询商品分类列表
	 * @param param
	 * @return
	 */
	@Override
	public List<Classify> selectList(ClassifyParam param) {
		List<Object> paramsList = new ArrayList<Object>();
		List<Classify> classifyList = new ArrayList<Classify>();
		StringBuffer sql = new StringBuffer("select * from t_classify where 1=1");
		if (EmptyUtils.isNotEmpty(param.getParentId())) {
			sql.append(" and parentId = ?");
			paramsList.add(param.getParentId());
		}
		if (EmptyUtils.isNotEmpty(param.getType())) {
			sql.append(" and type = ?");
			paramsList.add(param.getType());
		}
		if (EmptyUtils.isNotEmpty(param.getClassifyName())) {
			sql.append(" and classifyName like ?");
			paramsList.add("%" + param.getClassifyName() + "%");
		}
		if (EmptyUtils.isNotEmpty(param.getSort())) {
			sql.append(" order by " + param.getSort());
		}
		if (param.isPage()) {
			sql.append(" limit ?, ?");
			paramsList.add(param.getStartIndex());
			paramsList.add(param.getPageSize());
		}
		
		ResultSet rs = this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			while (rs.next()) {
				Classify classify = createEntityByResultSet(rs);
				classifyList.add(classify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
			this.closeResource(rs);
		}
		return classifyList;
	}
}
