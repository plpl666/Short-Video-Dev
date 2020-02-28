package com.imooc.pojo.vo;

import com.imooc.pojo.Users;

public class PublisherVideo {
    private Users user;
    private boolean userLikeVideo;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isUserLikeVideo() {
        return userLikeVideo;
    }

    public void setUserLikeVideo(boolean userLikeVideo) {
        this.userLikeVideo = userLikeVideo;
    }
}
