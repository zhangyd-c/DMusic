package me.zhyd.music.framework.cache;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Service
public class CustomeCache implements CustomConcurrentMapCache {
    private static final ScheduledThreadPoolExecutor TASK_POOL = new ScheduledThreadPoolExecutor(10, (ThreadFactory) Thread::new);
    private static final ConcurrentHashMap<String, Object> CACHE_CONTAINER = new ConcurrentHashMap<>();

    /**
     * 增加缓存对象
     *
     * @param key
     * @param value
     * @param time  有效时间 单位秒
     */
    @Override
    public void setCache(String key, Object value, long time) {
        CACHE_CONTAINER.put(key, new Cache(time, value));
        TASK_POOL.schedule(new TimeoutTimerTask(key, this), time, TimeUnit.MILLISECONDS);
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param value
     */
    @Override
    public void setCache(String key, Object value) {
        CACHE_CONTAINER.put(key, new Cache(value));
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @return 缓存内容
     */
    @Override
    public <T> T getCache(String key) {
        Cache cache = (Cache) CACHE_CONTAINER.get(key);
        if (cache == null) {
            return null;
        }
        return (T) cache.getValue();
    }

    /**
     * 检查是否含有制定key的缓冲
     *
     * @param key
     * @return true存在 ;false 不存在
     */
    @Override
    public Boolean contains(String key) {
        return CACHE_CONTAINER.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    @Override
    public void delCache(String key) {
        CACHE_CONTAINER.remove(key);
    }

    /**
     * 清除超时缓存定时服务类
     */
    static class TimeoutTimerTask extends TimerTask {
        private String ceKey;
        private CustomeCache cacheHandle;

        TimeoutTimerTask(String key, CustomeCache cacheHandle) {
            this.ceKey = key;
            this.cacheHandle = cacheHandle;
        }

        @Override
        public void run() {
            Cache cache = (Cache) CACHE_CONTAINER.get(ceKey);
            if (cache == null || cache.getDate() == null) {
                return;
            }
            if (System.currentTimeMillis() < cache.getDate().getTime()) {
                return;
            }
            cacheHandle.delCache(ceKey);
        }
    }

    private static class Cache {
        private Date date;
        private Object value;

        Cache(long time, Object value) {
            this.date = new Date(System.currentTimeMillis() + time);
            this.value = value;
        }

        Cache(Object value) {
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public Object getValue() {
            return value;
        }
    }
}
