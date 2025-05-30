package cn.interestingshop.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * MyBatis工具类
 */
public class MyBatisUtil {
    private static Logger logger = Logger.getLogger(MyBatisUtil.class);
    private static SqlSessionFactory sqlSessionFactory;
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            logger.error("MyBatis SqlSessionFactory 创建失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取SqlSession对象
     * @return SqlSession对象
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭SqlSession对象
     */
    public static void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();
        }
    }

    /**
     * 提交事务
     */
    public static void commit() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }

    /**
     * 获取Mapper接口的实现类
     * @param <T> Mapper接口类型
     * @param clazz Mapper接口Class对象
     * @return Mapper接口实现类
     */
    public static <T> T getMapper(Class<T> clazz) {
        return getSqlSession().getMapper(clazz);
    }
} 