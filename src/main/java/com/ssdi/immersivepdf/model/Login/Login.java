package com.ssdi.immersivepdf.model.Login;

public class Login {
    private String email;
    private String password;

    //NOTICE : This kind of initializer is not required for model classes and infact caused problem. Creating another blank constructor solved the problem and was able to map the json request to model classes. This construsuctor will be deleted in future commits
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Login(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
