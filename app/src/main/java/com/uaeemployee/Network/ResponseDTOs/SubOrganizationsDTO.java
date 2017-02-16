package com.uaeemployee.Network.ResponseDTOs;

/**
 * Created by hakhtar on 2/16/2017.
 * General Data
 */

public class SubOrganizationsDTO {

    private int SubOrgnizationID;
    private String OrganizationID;
    private String Name;
    private String Address;
    private String PoBox;
    private String ContactNo;
    private String Email;
    private String WebsiteURL;
    private String POC;

    public SubOrganizationsDTO(int subOrgnizationID, String organizationID, String name, String address, String poBox, String contactNo, String email, String websiteURL, String POC) {
        SubOrgnizationID = subOrgnizationID;
        OrganizationID = organizationID;
        Name = name;
        Address = address;
        PoBox = poBox;
        ContactNo = contactNo;
        Email = email;
        WebsiteURL = websiteURL;
        this.POC = POC;
    }

    public int getSubOrgnizationID() {
        return SubOrgnizationID;
    }

    public void setSubOrgnizationID(int subOrgnizationID) {
        SubOrgnizationID = subOrgnizationID;
    }

    public String getOrganizationID() {
        return OrganizationID;
    }

    public void setOrganizationID(String organizationID) {
        OrganizationID = organizationID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPoBox() {
        return PoBox;
    }

    public void setPoBox(String poBox) {
        PoBox = poBox;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }

    public String getPOC() {
        return POC;
    }

    public void setPOC(String POC) {
        this.POC = POC;
    }
}
