package com.example.redis.springbootrediscache.config;

import lombok.Setter;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Setter
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private int shortCacheInSeconds = 20;
    private int longCacheInHours = 2;

    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(shortCacheInSeconds)));
            configurationMap.put("employees", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(longCacheInHours)));
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }

}