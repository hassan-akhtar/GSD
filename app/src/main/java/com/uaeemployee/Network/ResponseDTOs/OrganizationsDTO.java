package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 2/16/2017.
 * General Data
 */

public class OrganizationsDTO extends ResponseDTO{

    private int OrganizationID;
    private String Name;
    private String Address;
    private String PoBox;
    private String ContactNo;
    private String Email;
    private String WebsiteURL;
    private String POC;
    private List<SubOrganizationsDTO> lstSubOrganization;
    private List<SubSubOrganizationsDTO> lstSubSubOrganization;

    public OrganizationsDTO(int organizationID, String name, String address, String poBox, String contactNo, String email, String websiteURL, String POC, List<SubOrganizationsDTO> lstSubOrganization, List<SubSubOrganizationsDTO> lstSubSubOrganization) {
        OrganizationID = organizationID;
        Name = name;
        Address = address;
        PoBox = poBox;
        ContactNo = contactNo;
        Email = email;
        WebsiteURL = websiteURL;
        this.POC = POC;
        this.lstSubOrganization = lstSubOrganization;
        this.lstSubSubOrganization = lstSubSubOrganization;
    }

    public List<SubOrganizationsDTO> getLstSubOrganization() {
        return lstSubOrganization;
    }

    public void setLstSubOrganization(List<SubOrganizationsDTO> lstSubOrganization) {
        this.lstSubOrganization = lstSubOrganization;
    }

    public List<SubSubOrganizationsDTO> getLstSubSubOrganization() {
        return lstSubSubOrganization;
    }

    public void setLstSubSubOrganization(List<SubSubOrganizationsDTO> lstSubSubOrganization) {
        this.lstSubSubOrganization = lstSubSubOrganization;
    }

    public int getOrganizationID() {
        return OrganizationID;
    }

    public void setOrganizationID(int organizationID) {
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
