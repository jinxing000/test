package com.ef.golf.pojo;

public class Calendar {
    private Integer id;

    private String cDate;

    private String cWeek;

    private String cStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate == null ? null : cDate.trim();
    }

    public String getcWeek() {
        return cWeek;
    }

    public void setcWeek(String cWeek) {
        this.cWeek = cWeek == null ? null : cWeek.trim();
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus == null ? null : cStatus.trim();
    }
}