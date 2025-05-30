package cn.interestingshop.dao.order;
import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.BaseOrder;

/***
 * 订单处理的dao层
 * getRowCount
 * getRowList(Params params)
 * getById(Integer id)
 * addObject(Params params)
 */
public interface BaseOrderDao extends IBaseDao {

	public void save(BaseOrder baseOrder) throws Exception;

	public void deleteById(Integer id) throws Exception;
	
	public BaseOrder selectById(Integer id) throws Exception;
	
	public List<BaseOrder> selectList(Integer userId,Integer currentPageNo,Integer pageSize) throws Exception;
	
	public Integer selectCount(Integer userId) throws Exception;
	
	public void updateStatus(Integer id, Integer status) throws Exception;
}
