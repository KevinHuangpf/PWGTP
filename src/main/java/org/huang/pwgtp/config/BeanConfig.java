package org.huang.pwgtp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean配置
 */
@Configuration
public class BeanConfig {

    @Bean
    //这个Bean用于密码加密，采用BCrypt算法。
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public RedissonClient redissonClient() {
        return Redisson.create();
    }

}
