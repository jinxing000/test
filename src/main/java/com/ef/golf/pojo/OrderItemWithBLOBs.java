package com.ef.golf.pojo;

public class OrderItemWithBLOBs extends OrderItem {
    private String image;

    private String addon;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon == null ? null : addon.trim();
    }
}