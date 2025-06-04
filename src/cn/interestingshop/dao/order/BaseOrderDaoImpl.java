package cn.interestingshop.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;

/**
 * 订单主表Dao实现
 */
public class BaseOrderDaoImpl extends BaseDaoImpl implements BaseOrderDao {

	private OrderMapper orderMapper;
	private SqlSession sqlSession;

	public BaseOrderDaoImpl(Connection connection, SqlSession sqlSession) {
		super(connection);
		this.sqlSession = sqlSession;
		this.orderMapper = sqlSession.getMapper(OrderMapper.class);
	}

	/**
	 * 根据ID查询订单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public BaseOrder selectById(Integer id) throws Exception {
		try {
			return orderMapper.selectBaseOrderById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 分页查询订单
	 * @param userId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BaseOrder> selectList(Integer userId, Integer currentPageNo, Integer pageSize) throws Exception {
		try {
			Integer count = selectCount(userId);
			Pager pager = new Pager(count, pageSize, currentPageNo);
			int offset = (pager.getCurrentPage() - 1) * pager.getRowPerPage();
			
			return orderMapper.selectBaseOrderList(offset, pager.getRowPerPage(), userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 统计订单数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer selectCount(Integer userId) throws Exception {
		try {
			return orderMapper.selectBaseOrderCount(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 保存订单
	 * @param baseOrder
	 * @throws Exception
	 */
	@Override
	public void save(BaseOrder baseOrder) throws Exception {
		try {
			int result = orderMapper.saveBaseOrder(baseOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改订单状态
	 * @param id
	 * @param status
	 * @throws Exception
	 */
	@Override
	public void updateStatus(Integer id, Integer status) throws Exception {
		try {
			orderMapper.updateOrderStatus(id, status);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 删除订单
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteById(Integer id) throws Exception {
		try {
			orderMapper.deleteBaseOrderById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public BaseOrder createEntityByResultSet(ResultSet rs) throws Exception {
		BaseOrder baseOrder = new BaseOrder();
		baseOrder.setId(rs.getInt("id"));
		baseOrder.setUserId(rs.getInt("userId"));
		baseOrder.setUserAddress(rs.getString("userAddress"));
		baseOrder.setCreateTime(rs.getDate("createTime"));
		baseOrder.setAmount(rs.getFloat("amount"));
		baseOrder.setOrderNo(rs.getString("orderNo"));
		baseOrder.setAccount(rs.getString("account"));
		return baseOrder;
	}
}
