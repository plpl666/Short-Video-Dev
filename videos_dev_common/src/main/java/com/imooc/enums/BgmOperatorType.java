package com.imooc.enums;

public enum BgmOperatorType {

    ADD("1","添加Bgm"),
    DELETE("0","删除Bgm");

    private final String type;
    private final String value;

    BgmOperatorType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static String getValueByKey(String key){
        for (BgmOperatorType type : BgmOperatorType.values()) {
            if (type.getType().equals(key)) {
                return type.getValue();
            }
        }
        return null;
    }

}
