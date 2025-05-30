package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;

/**
 * Created by bdqn on 2016/5/12.
 * addObject(UserAddress userAddress)
 * getRowList(params)
 * getRowCount(params)
 * getById(Integer id)
 * updateByQuery(params)
 */
public interface ClassifyDao extends IBaseDao {
	/**
	 * 根据id删除商品
	 * @param parseLong
	 */
	void deleteById(Integer parseLong) throws Exception;//删除商品分类
	/**
	 * 根据条件查询商品列表
	 * @param param
	 */
	public List<Classify> selectList(ClassifyParam param);
	/**
	 * 根据id查询商品分类
	 * @param param
	 */
	Classify selectById(Integer id) throws Exception;
	/**
	 * 添加商品分类
	 * @param param
	 */
	public Integer save(Classify classify) throws Exception;
	/**
	 * 根据参数查询商品分类的数目
	 * @param param
	 */
	public Integer selectCount(ClassifyParam param);
	/**
	 * 修改商品分类
	 * @param param
	 */
	public void update(Classify classify) throws Exception;
	
	/**
	 * 获取所有商品分类列表
	 */
	public List<Classify> selectAll() throws Exception;
	
	/**
	 * 获取分页商品分类列表
	 */
	public List<Classify> selectList(Integer currentPageNo, Integer pageSize) throws Exception;
	
	/**
	 * 统计商品分类数量
	 */
	public Integer selectCount() throws Exception;
}
