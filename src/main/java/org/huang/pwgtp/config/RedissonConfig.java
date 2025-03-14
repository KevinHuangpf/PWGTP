package org.huang.pwgtp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shkstart
 * @create 2025-03-14 下午8:17
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://113.45.38.36:6379")
                .setPassword("123456")
                .setConnectTimeout(30000)  // 默认 10000ms (10秒)，建议增加至 30秒
                .setTimeout(30000);  ;
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
