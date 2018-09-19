package me.zhyd.music.framework.cache;

public interface CustomConcurrentMapCache {
    /**
     * 设置缓存
     *
     * @param key   缓存KEY
     * @param value 缓存内容
     * @param time  缓存时间
     */
    void setCache(String key, Object value, long time);

    /**
     * 设置缓存
     *
     * @param key   缓存KEY
     * @param value 缓存内容
     */
    void setCache(String key, Object value);

    /**
     * 获取缓存
     *
     * @param key 缓存KEY
     * @return 缓存内容
     */
    <T> T getCache(String key);

    /**
     * 判断缓存是否存在
     *
     * @param key 缓存KEY
     * @return 缓存内容
     */
    Boolean contains(String key);

    /**
     * 删除缓存
     *
     * @param key 缓存KEY
     */
    void delCache(String key);
}
