package com.ef.golf.pojo;

import java.util.Date;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/5/5 16:05
 */
public class ByBlack {
    private Long id;
    private Long userId;
    private Long byBlackId;
    private Date blackDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(Date blackDate) {
        this.blackDate = blackDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getByBlackId() {
        return byBlackId;
    }

    public void setByBlackId(Long byBlackId) {
        this.byBlackId = byBlackId;
    }
}
