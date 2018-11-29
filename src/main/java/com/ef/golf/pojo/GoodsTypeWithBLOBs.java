package com.ef.golf.pojo;

public class GoodsTypeWithBLOBs extends GoodsType {
    private String props;

    private String params;

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props == null ? null : props.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}