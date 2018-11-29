package com.ef.golf.vo;

/**
 * 球会
 */
public class QiuhuiVo {

    private Integer id;//id
    private String nickName;//球会名字

    public QiuhuiVo() {
    }

    public QiuhuiVo(Integer id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
