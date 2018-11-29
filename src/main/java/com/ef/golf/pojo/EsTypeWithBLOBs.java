package com.ef.golf.pojo;

public class EsTypeWithBLOBs extends EsType {
    private String detail;

    private String config;

    private String expressions;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }

    public String getExpressions() {
        return expressions;
    }

    public void setExpressions(String expressions) {
        this.expressions = expressions == null ? null : expressions.trim();
    }
}