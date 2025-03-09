package org.huang.pwgtp.common.bizEnum;

import java.util.Arrays;
/**
 * 行程类型枚举
 */
public enum TravelTypeEnum {
    COMMUTING("拼通勤"),
    JOURNEY("拼旅行");

    private final String desc;

    TravelTypeEnum(String desc) {
        this.desc = desc;
    }

    public static String getDescByName(String name) {
        return Arrays.stream(TravelTypeEnum.values()).filter(item -> item.name().equals(name)).findFirst().map(item -> item.desc).orElse(null);
    }
}
