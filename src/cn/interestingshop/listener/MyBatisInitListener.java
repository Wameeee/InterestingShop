package cn.interestingshop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cn.interestingshop.utils.MyBatisUtil;

/**
 * MyBatis初始化监听器
 * 在Web应用启动时初始化MyBatis配置
 */
public class MyBatisInitListener implements ServletContextListener {
    
    private static final Logger logger = Logger.getLogger(MyBatisInitListener.class);
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("初始化MyBatis配置...");
        // 初始化MyBatis，加载MyBatisUtil类触发静态初始化块
        try {
            // 加载工具类，触发静态块初始化
            Class.forName(MyBatisUtil.class.getName());
            logger.info("MyBatis配置初始化成功！");
        } catch (ClassNotFoundException e) {
            logger.error("MyBatis配置初始化失败！", e);
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Web应用关闭，释放MyBatis资源...");
    }
} 