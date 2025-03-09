package org.huang.pwgtp.common.bizEnum;


import java.util.Arrays;
/**
 * 出行状态枚举
 */
public enum TravelStatusEnum {

    DRAFT("草稿"),
    PUBLISHED("已发布招募中"),
    WAIT_TRAVEL("准备完毕待出行"),
    ENDED("已结束");

    private final String desc;

    private TravelStatusEnum(String desc) {
        this.desc = desc;
    }

    public static String getDescByName(String name) {
        return Arrays.stream(TravelStatusEnum.values()).filter(item -> item.name().equals(name)).findFirst().map(item -> item.desc).orElse(null);
    }

}
