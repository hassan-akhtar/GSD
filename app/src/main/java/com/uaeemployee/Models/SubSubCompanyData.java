package com.uaeemployee.Models;

/**
 * Created by hakhtar on 2/10/2017.
 * General Data
 */

public class SubSubCompanyData {

    private String name;
    private String maleCount;
    private String femaleCount;
    private String localCount;

    public SubSubCompanyData(String name, String maleCount, String femaleCount, String localCount) {
        this.name = name;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.localCount = localCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    public String getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

    public String getLocalCount() {
        return localCount;
    }

    public void setLocalCount(String localCount) {
        this.localCount = localCount;
    }
}
