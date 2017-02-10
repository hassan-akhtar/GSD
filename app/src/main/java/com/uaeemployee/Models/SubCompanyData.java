package com.uaeemployee.Models;

/**
 * Created by hakhtar on 2/10/2017.
 * General Data
 */

public class SubCompanyData {

    private String name;
    private String subSubCompanyCount;

    public SubCompanyData(String name, String subSubCompanyCount) {
        this.name = name;
        this.subSubCompanyCount = subSubCompanyCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubSubCompanyCount() {
        return subSubCompanyCount;
    }

    public void setSubSubCompanyCount(String subSubCompanyCount) {
        this.subSubCompanyCount = subSubCompanyCount;
    }
}
