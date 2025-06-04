package cn.interestingshop.dao.goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.utils.ThreadLocalContext;

/**
 * 商品dao实现类
 */
public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDao {

    private GoodsMapper goodsMapper;
    private SqlSession sqlSession;

    public GoodsDaoImpl(Connection connection, SqlSession sqlSession) {
        super(connection);
        this.sqlSession = sqlSession;
        this.goodsMapper = sqlSession.getMapper(GoodsMapper.class);
    }

    /**
     * 根据id商品列表
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Goods selectById(Integer id) throws Exception {
        try {
            return goodsMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 查询商品列表
     * @param goodsName
     * @param goodsType
     * @param currentPageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Goods> selectList(Integer currentPageNo, Integer pageSize, String goodsName, Integer goodsType) throws Exception {
        try {
            // 获取level值，从ThreadLocal中获取
            Integer level = ThreadLocalContext.getLevel();
            
            Integer count = goodsMapper.selectCount(goodsType, goodsName, level);
            Pager pager = new Pager(count, pageSize, currentPageNo);

            // 修复：确保当前页码至少为1
            int currentPage = Math.max(pager.getCurrentPage(), 1);
            int offset = (currentPage - 1) * pager.getRowPerPage();

            return selectGoodsList(offset, pager.getRowPerPage(), goodsType, goodsName, level);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 适配方法：确保参数顺序与GoodsMapper.selectList一致
     * @param offset
     * @param limit
     * @param classifyId
     * @param name
     * @param level 分类级别
     * @return
     * @throws Exception
     */
    private List<Goods> selectGoodsList(int offset, int limit, Integer classifyId, String name, Integer level) throws Exception {
        return goodsMapper.selectList(offset, limit, classifyId, name, level);
    }

    /**
     * 查询数据总数
     * @param goodsName
     * @param goodsType
     * @return
     * @throws Exception
     */
    @Override
    public Integer selectCount(String goodsName, Integer goodsType) throws Exception {
        try {
            // 获取level值，从ThreadLocal中获取
            Integer level = ThreadLocalContext.getLevel();
            
            // 使用适配方法调用mapper，正确处理参数顺序
            return selectGoodsCount(goodsType, goodsName, level);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 适配方法：确保参数顺序与GoodsMapper.selectCount一致
     * @param classifyId
     * @param name
     * @param level 分类级别
     * @return
     * @throws Exception
     */
    private int selectGoodsCount(Integer classifyId, String name, Integer level) throws Exception {
        return goodsMapper.selectCount(classifyId, name, level);
    }

    /**
     * 保存商品信息
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public Integer save(Goods goods) throws Exception {
        try {
            int result = goodsMapper.save(goods);
            return goods.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 更新商品信息
     * @param goods
     * @throws Exception
     */
    @Override
    public Integer update(Goods goods) throws Exception {
        try {
            int result = goodsMapper.update(goods);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除商品信息
     * @param id
     * @throws Exception
     */
    @Override
    public Integer deleteById(Integer id) throws Exception {
        try {
            int result = goodsMapper.deleteById(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Goods createEntityByResultSet(ResultSet rs) throws Exception {
        Goods goods = new Goods();
        goods.setId(rs.getInt("id"));
        goods.setName(rs.getString("goodsName"));
        goods.setDescription(rs.getString("goodsDesc"));
        goods.setPrice(rs.getFloat("price"));
        goods.setStock(rs.getInt("stock"));
        goods.setClassifyLevel1Id(rs.getInt("classifyLevel1Id"));
        goods.setClassifyLevel2Id(rs.getInt("classifyLevel2Id"));
        goods.setClassifyLevel3Id(rs.getInt("classifyLevel3Id"));
        goods.setFileName(rs.getString("fileName"));
        goods.setIsDelete(rs.getInt("isDelete"));
        return goods;
    }

    @Override
    public Integer updateStock(Integer id, Integer buyNum) throws Exception {
        try {
            Goods goods = goodsMapper.selectById(id);
            if (goods != null && goods.getStock() >= buyNum) {
                goods.setStock(goods.getStock() - buyNum);
                int result = goodsMapper.update(goods);
                return result;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
