package com.imooc.enums;

public enum VideoStatus {

    SUCCESS(1), //发布
    FORBID(2);  //禁播

    public final int value;

    VideoStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
