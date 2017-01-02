package com.ysp.smdemo.common.configs;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AddressProvider;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

/**
 * @author: shipeng.yu
 * @time: 2016年10月09日 下午4:36
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Configuration
public class MemCacheConfig extends CachingConfigurerSupport {

    private static final String LOCALHOST = "localhost:11211";

    private static final String USER_CACHE = "userCache";

    @Autowired
    private Environment environment;

    @Bean
    public CacheManager cacheManager() {
        MemcacheClientFactoryImpl cacheClientFactory = new MemcacheClientFactoryImpl();

        final String servers = environment.getProperty("memcached.servers");

        AddressProvider addressProvider;

        if (StringUtils.isEmpty(servers))
            addressProvider = new DefaultAddressProvider(LOCALHOST);
        else
            addressProvider = new DefaultAddressProvider(servers);

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setConsistentHashing(true);

        CacheFactory cacheFactory = new CacheFactory();

        cacheFactory.setCacheName(USER_CACHE);

        cacheFactory.setCacheClientFactory(cacheClientFactory);
        cacheFactory.setAddressProvider(addressProvider);
        cacheFactory.setConfiguration(cacheConfiguration);

        Cache object = null;
        try {
            object = cacheFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 10000 表示超时时间,我们也可以配置在 application.yml 文件中
        SSMCache ssmCache = new SSMCache(object, 10000, true);

        ArrayList<SSMCache> ssmCaches = new ArrayList<>();
        ssmCaches.add(0, ssmCache);

        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        ssmCacheManager.setCaches(ssmCaches);

        return ssmCacheManager;
    }
}