package com.wjf.write_source_code.enumpack;

public enum OneParamEnum {
    Yes("1"),
    NO("2");
    private String code;

    OneParamEnum(String code) {
        this.code = code;
    }
}
