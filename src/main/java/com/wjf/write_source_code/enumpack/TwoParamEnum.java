package com.wjf.write_source_code.enumpack;

public enum TwoParamEnum {
    YES("1","是"),
    NO("1","否");
    private String code;
    private String desc;

    TwoParamEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
