package org.huang.pwgtp.util;

import com.alibaba.fastjson.JSON;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedissonLockUtil {
 
    @Autowired
    private RedissonClient redissonClient;
 
    public boolean lock(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }
 
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        if (lock.isLocked()) {
            lock.unlock();
        }
    }
 
}