package org.huang.pwgtp.common.bizEnum;

import java.util.Arrays;
/**
 * 收藏类型枚举
 */
public enum FavoriteTypeEnum {

    TRAVEL("行程"),
    ARTICLE("文章"),

    ;
    private final String desc;

    private FavoriteTypeEnum(String desc) {
        this.desc = desc;
    }

    public static String getDescByName(String name) {
        return Arrays.stream(FavoriteTypeEnum.values()).filter(item -> item.name().equals(name)).findFirst().map(item -> item.desc).orElse(null);
    }
}
