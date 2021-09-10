package com.example.myfirstproject.model;

import cn.bmob.v3.BmobObject;

public class Information extends BmobObject {

    //上传人
    private User author;

    private String username;

    //下面三个是资讯的信息
    private String title;

    private String content;

    private String nickname;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
