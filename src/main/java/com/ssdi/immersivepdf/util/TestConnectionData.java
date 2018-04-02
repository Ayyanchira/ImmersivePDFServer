package com.ssdi.immersivepdf.util;

import com.ssdi.immersivepdf.config.Constants;

public class TestConnectionData implements IConnectionData{
    @Override
    public String getUrl() {
        return Constants.databaseEnvironmentTest;
    }

    @Override
    public String getUser() {
        return "root";
    }

    @Override
    public String getPassword() {
        return "syntel123$";
    }
}
