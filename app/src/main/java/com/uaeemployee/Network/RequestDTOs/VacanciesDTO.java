package com.uaeemployee.Network.RequestDTOs;

/**
 * Created by hakhtar on 3/15/2017.
 * General Data
 */

public class VacanciesDTO extends BaseDTO{

    private int OrganizationID;
    private int StatusID;
    private int LoginEmpID;
    private int GenderType;
    private String Nationality;

    public VacanciesDTO(int callBackId, int organizationID, int statusID, int loginEmpID) {
        super(callBackId);
        OrganizationID = organizationID;
        StatusID = statusID;
        LoginEmpID = loginEmpID;
    }

    public VacanciesDTO(int callBackId, int organizationID, int statusID, int loginEmpID, int genderType, String nationality) {
        super(callBackId);
        OrganizationID = organizationID;
        StatusID = statusID;
        LoginEmpID = loginEmpID;
        GenderType = genderType;
        Nationality = nationality;
    }

    public VacanciesDTO(int callBackId, int loginEmpID) {
        super(callBackId);
        LoginEmpID = loginEmpID;
    }

    public int getGenderType() {
        return GenderType;
    }

    public void setGenderType(int genderType) {
        GenderType = genderType;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public int getOrganizationID() {
        return OrganizationID;
    }

    public void setOrganizationID(int organizationID) {
        OrganizationID = organizationID;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public int getLoginEmpID() {
        return LoginEmpID;
    }

    public void setLoginEmpID(int loginEmpID) {
        LoginEmpID = loginEmpID;
    }
}