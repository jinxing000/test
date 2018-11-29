package com.ef.golf.pojo;

public class Es_Settings {
    private Integer id;

    private String code;

    private String cfgValue;

    private String cfgGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue == null ? null : cfgValue.trim();
    }

    public String getCfgGroup() {
        return cfgGroup;
    }

    public void setCfgGroup(String cfgGroup) {
        this.cfgGroup = cfgGroup == null ? null : cfgGroup.trim();
    }
}