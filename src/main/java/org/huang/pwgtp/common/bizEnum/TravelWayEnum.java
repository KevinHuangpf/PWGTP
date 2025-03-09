package org.huang.pwgtp.common.bizEnum;

import java.sql.Array;
import java.util.Arrays;

/**
 * 出行方式枚举
 */
public enum TravelWayEnum {

    SELF_DRIVING("自驾"),
    TRAIN("火车/高铁"),
    AIRPLANE("飞机"),
    RIDING("骑行"),
    WALK("步行"),

    ;
    private String desc;

    private TravelWayEnum(String desc) {
        this.desc = desc;
    }

    public static String getDescByName(String name) {
        return Arrays.stream(TravelWayEnum.values()).filter(item -> item.name().equals(name)).findFirst().map(item -> item.desc).orElse(null);
    }
}
