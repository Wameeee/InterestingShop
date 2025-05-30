package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.entity.Classify;
import org.apache.ibatis.annotations.Param;

/**
 * ClassifyMapper接口 - MyBatis版本
 */
public interface ClassifyMapper {
    
    /**
     * 保存分类信息
     * @param classify 分类对象
     * @return 影响的行数
     */
    int save(Classify classify);
    
    /**
     * 更新分类信息
     * @param classify 分类对象
     * @return 影响的行数
     */
    int update(Classify classify);
    
    /**
     * 根据ID删除分类
     * @param id 分类ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
    
    /**
     * 分页查询分类列表
     * @param offset 起始位置
     * @param limit 每页条数
     * @return 分类列表
     */
    List<Classify> selectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
    
    /**
     * 查询所有分类列表
     * @return 所有分类列表
     */
    List<Classify> selectAll();
    
    /**
     * 查询分类总数
     * @return 分类总数
     */
    int selectCount();
    
    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类对象
     */
    Classify selectById(Integer id);
} 