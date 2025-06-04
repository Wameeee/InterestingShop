package cn.interestingshop.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * MyBatis工具类，简化版本
 */
public class MyBatisUtil {
    private static Logger logger = Logger.getLogger(MyBatisUtil.class);
    private static SqlSessionFactory sqlSessionFactory;

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
     * 获取SqlSessionFactory
     * @return SqlSessionFactory对象
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     * 创建新的SqlSession
     * @return SqlSession对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 创建自动提交的SqlSession
     * @return SqlSession对象
     */
    public static SqlSession openSessionWithAutoCommit() {
        return sqlSessionFactory.openSession(true);
    }
} 