package com.wotung.integration.member.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "member.login")
public class MemberLoginProperties {
    private int lockInterval;
    private int allowedErrorTime;
//    public long ttlMillis;

    public int getLockInterval() {
        return lockInterval;
    }

    public void setLockInterval(int lockInterval) {
        this.lockInterval = lockInterval;
    }

    public int getAllowedErrorTime() {
        return allowedErrorTime;
    }

    public void setAllowedErrorTime(int allowedErrorTime) {
        this.allowedErrorTime = allowedErrorTime;
    }

//    public long getTtlMillis() {
//        return ttlMillis;
//    }
//
//    public void setTtlMillis(long ttlMillis) {
//        this.ttlMillis = ttlMillis;
//    }
}
