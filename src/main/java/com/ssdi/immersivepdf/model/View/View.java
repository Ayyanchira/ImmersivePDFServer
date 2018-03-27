package com.ssdi.immersivepdf.model.View;

public class View {
    private String email;

    //NOTICE : This kind of initializer is not required for model classes and infact caused problem. Creating another blank constructor solved the problem and was able to map the json request to model classes. This construsuctor will be deleted in future commits
    public View(String email) {
        this.email = email;
    }

    public View(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
