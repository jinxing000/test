package com.ef.golf.pojo;

/**
 * com.ef.golf.action
 * Administrator
 * 2018/5/29 14:45
 */
public class Groups {

  private  String groupId;
    private  String    name;
    private  String     count;
    private String     type;
    private String    permission;
    private String owner;
    private String headPortraitUrl;

    private Boolean hasIn;

    public Boolean getHasIn() {
        return hasIn;
    }

    public void setHasIn(Boolean hasIn) {
        this.hasIn = hasIn;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
