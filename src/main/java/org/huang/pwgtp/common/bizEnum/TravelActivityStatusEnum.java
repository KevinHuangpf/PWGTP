package org.huang.pwgtp.common.bizEnum;


public enum TravelActivityStatusEnum {

    DRAFT("草稿"),
    PUBLISHED("已发布招募中"),
    WAIT_TRAVEL("准备完毕待出行"),
    ENDED("已结束");

    private TravelActivityStatusEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

}
