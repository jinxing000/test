package com.ef.golf.pojo;

public class EsTypeAreaWithBLOBs extends EsTypeArea {
    private String areaIdGroup;

    private String areaNameGroup;

    private String expressions;

    private String config;

    public String getAreaIdGroup() {
        return areaIdGroup;
    }

    public void setAreaIdGroup(String areaIdGroup) {
        this.areaIdGroup = areaIdGroup == null ? null : areaIdGroup.trim();
    }

    public String getAreaNameGroup() {
        return areaNameGroup;
    }

    public void setAreaNameGroup(String areaNameGroup) {
        this.areaNameGroup = areaNameGroup == null ? null : areaNameGroup.trim();
    }

    public String getExpressions() {
        return expressions;
    }

    public void setExpressions(String expressions) {
        this.expressions = expressions == null ? null : expressions.trim();
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }
}