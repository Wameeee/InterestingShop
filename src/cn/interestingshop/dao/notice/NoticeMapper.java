package cn.interestingshop.dao.notice;

import java.util.List;

import cn.interestingshop.entity.Notice;
import org.apache.ibatis.annotations.Param;

/**
 * NoticeMapper接口 - MyBatis版本
 */
public interface NoticeMapper {
    
    /**
     * 保存公告信息
     * @param notice 公告对象
     * @return 影响的行数
     */
    int save(Notice notice);
    
    /**
     * 更新公告信息
     * @param notice 公告对象
     * @return 影响的行数
     */
    int update(Notice notice);
    
    /**
     * 根据ID删除公告
     * @param id 公告ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
    
    /**
     * 分页查询公告列表
     * @param offset 起始位置
     * @param limit 每页条数
     * @param title 公告标题（可选）
     * @return 公告列表
     */
    List<Notice> selectList(@Param("offset") Integer offset, 
                            @Param("limit") Integer limit,
                            @Param("title") String title);
    
    /**
     * 查询公告总数
     * @param title 公告标题（可选）
     * @return 公告总数
     */
    int selectCount(@Param("title") String title);
    
    /**
     * 根据ID查询公告
     * @param id 公告ID
     * @return 公告对象
     */
    Notice selectById(Integer id);
} 