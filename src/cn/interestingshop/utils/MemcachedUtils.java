package cn.interestingshop.utils;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Memcached utility with fallback to local cache
 */
public class MemcachedUtils {

    private static final Logger logger = Logger.getLogger(MemcachedUtils.class.getName());
    private static MemCachedClient client = null;
    private static boolean memcachedAvailable = false;
    
    // Fallback local cache when Memcached is not available
    private static final ConcurrentHashMap<String, Object> localCache = new ConcurrentHashMap<>();

    static String[] connectUrls = new String[]{"127.0.0.1:11211"};

    static {
        try {
            // 初始化 Memcached 客户端
            client = new MemCachedClient();
            
            // 设置和初始化连接池
            try {
                SockIOPool pool = SockIOPool.getInstance();
                pool.setServers(connectUrls);
                pool.setWeights(new Integer[]{3});
                pool.setInitConn(5);
                pool.setMinConn(5);
                pool.setMaxConn(200);
                pool.setMaxIdle(1000 * 30 * 30);
                pool.setMaintSleep(30);
                pool.setNagle(false);
                pool.setSocketConnectTO(30);
                pool.setSocketTO(3000); // Set socket timeout
                pool.initialize();
                
                // 测试连接
                try {
                    client.set("test_key", "test_value");
                    Object result = client.get("test_key");
                    memcachedAvailable = "test_value".equals(result);
                    
                    if (memcachedAvailable) {
                        logger.info("Memcached connection successful");
                    } else {
                        logger.warning("Memcached test failed - falling back to local cache");
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Memcached connection test failed - falling back to local cache", e);
                    memcachedAvailable = false;
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Memcached pool initialization failed - falling back to local cache", e);
                memcachedAvailable = false;
            }
        } catch (Throwable e) {
            logger.log(Level.WARNING, "Memcached client initialization failed - falling back to local cache", e);
            memcachedAvailable = false;
        }
        
        logger.info("MemcachedUtils initialized. Using Memcached: " + memcachedAvailable);
    }

    public static void add(String key, Object object) {
        if (key == null) return;
        
        try {
            if (memcachedAvailable) {
                client.set(key, object);
            } else {
                // Fallback to local cache
                localCache.put(key, object);
            }
        } catch (Exception e) {
            // If Memcached fails, use local cache
            logger.log(Level.WARNING, "Memcached set operation failed for key: " + key, e);
            localCache.put(key, object);
            memcachedAvailable = false;
        }
    }

    public static void del(String key) {
        if (key == null) return;
        
        try {
            if (memcachedAvailable) {
                client.delete(key);
            }
            // Always remove from local cache
            localCache.remove(key);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Memcached delete operation failed for key: " + key, e);
            localCache.remove(key);
            memcachedAvailable = false;
        }
    }

    public static Object get(String key) {
        if (key == null) return null;
        
        try {
            if (memcachedAvailable) {
                try {
                    Object value = client.get(key);
                    if (value != null) {
                        return value;
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Memcached get operation failed for key: " + key, e);
                    memcachedAvailable = false;
                }
            }
            // If memcached failed or returned null, check local cache
            return localCache.get(key);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Cache get operation failed for key: " + key, e);
            return null;
        }
    }

    // For testing purposes only
    public static void main(String args[]) {
        List<String> name = new ArrayList<String>();
        name.add("1111");
        name.add("2222");
        name.add("3333");
        name.add("4444");
        name.add("5555");
        name.add("6666");
        add("name", name);
        List<String> test = (List<String>) get("name");
        System.out.print(test);
        
        System.out.println("\nUsing Memcached: " + memcachedAvailable);
        if (!memcachedAvailable) {
            System.out.println("Fallback to local cache is active");
        }
    }
}
