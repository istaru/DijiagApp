package com.hb.zll.dijiag.entity;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class BannerEntity {
    private String name;
    private String avatar;

    public BannerEntity() {
    }

    public BannerEntity(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
