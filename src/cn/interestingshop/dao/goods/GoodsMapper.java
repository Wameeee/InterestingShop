package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.entity.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * GoodsMapper接口 - MyBatis版本
 */
public interface GoodsMapper {
    
    /**
     * 保存商品信息
     * @param goods 商品对象
     * @return 影响的行数
     */
    int save(Goods goods);
    
    /**
     * 更新商品信息
     * @param goods 商品对象
     * @return 影响的行数
     */
    int update(Goods goods);
    
    /**
     * 根据ID删除商品
     * @param id 商品ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
    
    /**
     * 分页查询商品列表
     * @param offset 起始位置
     * @param limit 每页条数
     * @param classifyId 分类ID
     * @param name 商品名称
     * @param level 分类级别(1,2,3)
     * @return 商品列表
     */
    List<Goods> selectList(@Param("offset") Integer offset, 
                          @Param("limit") Integer limit,
                          @Param("classifyId") Integer classifyId,
                          @Param("name") String name,
                          @Param("level") Integer level);
    
    /**
     * 查询商品总数
     * @param classifyId 分类ID
     * @param name 商品名称
     * @param level 分类级别(1,2,3)
     * @return 商品总数
     */
    int selectCount(@Param("classifyId") Integer classifyId, 
                   @Param("name") String name,
                   @Param("level") Integer level);
    
    /**
     * 根据ID查询商品
     * @param id 商品ID
     * @return 商品对象
     */
    Goods selectById(Integer id);
} 