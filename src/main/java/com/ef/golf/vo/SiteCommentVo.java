package com.ef.golf.vo;

import java.io.Serializable;

/**
 * 球场评论数据
 */
public class SiteCommentVo implements Serializable {

    private Integer user_id;//评论者id
    private String nickname;//昵称
    private Integer comments_id;//评论id
    private Integer product_id;//被评论的球场id
    private Integer comments_type;//评论类型
    private Integer praise_num;//点赞数量
    private String content;//评论内容
    private Integer is_del;//是否被删除
    private String is_praise;//是否被点赞
    private String head_portrait_url;// 评论人头像



    public SiteCommentVo(){

    }

    public SiteCommentVo(Integer user_id, String nickname, Integer comments_id, Integer product_id, Integer comments_type, Integer praise_num, String content, Integer is_del, String is_praise) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.comments_id = comments_id;
        this.product_id = product_id;
        this.comments_type = comments_type;
        this.praise_num = praise_num;
        this.content = content;
        this.is_del = is_del;
        this.is_praise = is_praise;
    }



    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getComments_id() {
        return comments_id;
    }

    public void setComments_id(Integer comments_id) {
        this.comments_id = comments_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getComments_type() {
        return comments_type;
    }

    public void setComments_type(Integer comments_type) {
        this.comments_type = comments_type;
    }

    public Integer getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(Integer praise_num) {
        this.praise_num = praise_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }
    public String getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(String is_praise) {
        this.is_praise = is_praise;
    }

    public String getHead_portrait_url() {
        return head_portrait_url;
    }

    public void setHead_portrait_url(String head_portrait_url) {
        this.head_portrait_url = head_portrait_url;
    }
}
