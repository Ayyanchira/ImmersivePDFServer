package com.ssdi.immersivepdf.model.Register;

//@JsonInclude(Include.NON_NULL)
public class RegistrationRequest {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}