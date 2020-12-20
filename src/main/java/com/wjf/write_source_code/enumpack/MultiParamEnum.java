package com.wjf.write_source_code.enumpack;

public enum MultiParamEnum {
    soc_payment("1","zzz_soc_paymewnt","SBJN",true);
    private String key;
    private String processKey;
    private String processCode;
    private boolean isAddValid;

    MultiParamEnum(String key, String processKey, String processCode, boolean isAddValid) {
        this.key = key;
        this.processKey = processKey;
        this.processCode = processCode;
        this.isAddValid = isAddValid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public boolean isAddValid() {
        return isAddValid;
    }

    public void setAddValid(boolean addValid) {
        isAddValid = addValid;
    }
}
