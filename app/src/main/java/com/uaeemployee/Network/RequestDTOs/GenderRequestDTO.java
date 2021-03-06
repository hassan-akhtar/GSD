package com.uaeemployee.Network.RequestDTOs;



public class GenderRequestDTO extends BaseDTO {

    private int OrganizationID;
    private int StatusID;
    private int LoginEmpID;


    public GenderRequestDTO( int organizationID, int statusID, int loginEmpID) {
        OrganizationID = organizationID;
        StatusID = statusID;
        LoginEmpID = loginEmpID;
    }

    public GenderRequestDTO(int callBackId, int organizationID, int statusID, int loginEmpID) {
        super(callBackId);
        OrganizationID = organizationID;
        StatusID = statusID;
        LoginEmpID = loginEmpID;
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
