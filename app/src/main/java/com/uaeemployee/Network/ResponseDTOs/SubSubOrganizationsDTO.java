package com.uaeemployee.Network.ResponseDTOs;

/**
 * Created by hakhtar on 2/16/2017.
 * General Data
 */

public class SubSubOrganizationsDTO {

    private int SubSubOrgnizationID;
    private int SubOrganizationID;
    private String Name;
    private String Address;
    private String PoBox;
    private String ContactNo;
    private String Email;
    private String WebsiteURL;
    private String POC;


    public SubSubOrganizationsDTO(int subSubOrgnizationID, int subOrganizationID, String name, String address, String poBox, String contactNo, String email, String websiteURL, String POC) {
        SubSubOrgnizationID = subSubOrgnizationID;
        SubOrganizationID = subOrganizationID;
        Name = name;
        Address = address;
        PoBox = poBox;
        ContactNo = contactNo;
        Email = email;
        WebsiteURL = websiteURL;
        this.POC = POC;
    }

    public int getSubSubOrgnizationID() {
        return SubSubOrgnizationID;
    }

    public void setSubSubOrgnizationID(int subSubOrgnizationID) {
        SubSubOrgnizationID = subSubOrgnizationID;
    }

    public int getSubOrganizationID() {
        return SubOrganizationID;
    }

    public void setSubOrganizationID(int subOrganizationID) {
        SubOrganizationID = subOrganizationID;
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
