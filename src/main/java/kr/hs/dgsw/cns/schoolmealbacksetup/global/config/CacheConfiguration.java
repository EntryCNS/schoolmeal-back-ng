package kr.hs.dgsw.cns.schoolmealbacksetup.global.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@Configuration
public class CacheConfiguration {

    private static final String[] CACHE_STORAGE_NAMES = {
            "MealPlannerInfra" // 급식 API 캐시 스토리지
    };

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();

        for(String storageName : CACHE_STORAGE_NAMES)
            caches.add(new ConcurrentMapCache(storageName));

        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }
}
