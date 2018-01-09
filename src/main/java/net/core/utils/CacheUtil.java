package net.core.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * EhCache缓存工具类
 *
 * @author 汤国栋
 * @date 2016年11月23日 下午4:56:32
 */
public final class CacheUtil {

	/*
     * @Cacheable：如果使用@Cacheable注解，先检查对应的缓存是否存在，如果缓存存在，直接取缓存值返回，不再执行方法体内容；
	 * 如果缓存不存在，则执行方法体内容，并且将返回的内容写入缓存
	 * 
	 * @CachePut：使用@CachePut注解和@Cacheable注解类似，只是不管对应缓存是否存在，都执行方法体，并且将返回的内容写入缓存
	 * 
	 * @CacheEvict：@CacheEvict注解表示对应缓存的删除
	 */

    /**
     * 获取缓存中的信息
     *
     * @param cacheName   缓存名称
     * @param elementName 缓存中的主键名
     * @return
     */
    public static Element getElement(String cacheName, String elementName) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        // 获取ehcache事务
        CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
        // 获取缓存域
        Cache cache = cacheManager.getCache(cacheName);
        // 获取缓存域中的值
        Element element = cache.get(elementName);
        return element;
    }

}
