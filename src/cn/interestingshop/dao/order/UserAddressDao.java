package cn.interestingshop.dao.order;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;

/**
 * Created by bdqn on 2016/5/12.
 * addObject(UserAddress userAddress)
 * getRowList(params)
 */
public interface UserAddressDao extends IBaseDao {
	
	public List<UserAddress> selectList(UserAddressParam param);
	
	public Integer save(UserAddress userAddress) throws Exception;
	
	public UserAddress selectById(Integer addressId) throws Exception;
	
	public List<UserAddress> selectList(Integer userId) throws Exception;
	
	public void update(UserAddress userAddress) throws Exception;
	
	public void deleteById(Integer id) throws Exception;
}
