package cn.interestingshop.service.goods;

import java.util.List;

import cn.interestingshop.entity.Goods;

public interface GoodsService {
	
	public boolean save(Goods goods);
	
	public boolean update(Goods goods);
	
	public boolean deleteById(Integer goodsId);
	
	public Goods getById(Integer goodsId);
	
	public List<Goods> getList(Integer currentPageNo,Integer pageSize,
										String goodsName,Integer categoryId);
	
	/**
	 * 根据分类级别查询商品列表
	 * @param currentPageNo 当前页码
	 * @param pageSize 每页条数
	 * @param goodsName 商品名称
	 * @param categoryId 分类ID
	 * @param level 分类级别(1,2,3)
	 * @return 商品列表
	 */
	public List<Goods> getListByLevel(Integer currentPageNo, Integer pageSize,
                                     String goodsName, Integer categoryId, Integer level);
	
	public int getCount(String goodsName,Integer categoryId);
	
	/**
	 * 根据分类级别查询商品总数
	 * @param goodsName 商品名称
	 * @param categoryId 分类ID
	 * @param level 分类级别(1,2,3)
	 * @return 商品总数
	 */
	public int getCountByLevel(String goodsName, Integer categoryId, Integer level);
	
	public boolean updateStock(Integer goodsId,Integer stock);
	
}
