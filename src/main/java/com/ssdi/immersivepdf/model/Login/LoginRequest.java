package com.ssdi.immersivepdf.model.Login;

import com.ssdi.immersivepdf.model.Register.User;

public class LoginRequest {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
