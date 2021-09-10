package com.example.myfirstproject.model;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;
}
