package com.uaeemployee.Network.ResponseDTOs;

import java.io.Serializable;

public class NationDTO implements Serializable {

    private int Count;
    private String Name;
    private String FlagURL;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFlagURL() {
        return FlagURL;
    }

    public void setFlagURL(String flagURL) {
        FlagURL = flagURL;
    }
}
