package com.uaeemployee.Network.RequestDTOs;


public class OrganizationsDTO extends BaseDTO{

    private int UserID;

    public OrganizationsDTO(int callBackId, int userID) {
        super(callBackId);
        UserID = userID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
