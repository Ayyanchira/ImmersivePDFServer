package com.ssdi.immersivepdf.util;

import com.ssdi.immersivepdf.config.Constants;

public class ConnectionData implements IConnectionData{


    @Override
    public String getUrl() {
        return Constants.databaseEnvironmentProduction;
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
