package com.uaeemployee.Models;

/**
 * Created by hakhtar on 2/10/2017.
 * General Data
 */

public class Organization {

    private String name;
    private String subCompaniesCount;

    public Organization(String name, String subCompaniesCount) {
        this.name = name;
        this.subCompaniesCount = subCompaniesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCompaniesCount() {
        return subCompaniesCount;
    }

    public void setSubCompaniesCount(String subCompaniesCount) {
        this.subCompaniesCount = subCompaniesCount;
    }
}
