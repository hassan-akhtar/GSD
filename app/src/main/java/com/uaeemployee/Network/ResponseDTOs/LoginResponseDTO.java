package com.uaeemployee.Network.ResponseDTOs;

/**
 * Created by hakhtar on 2/14/2017.
 * General Data
 */

public class LoginResponseDTO extends  ResponseDTO{
    private int UserID;
    private String UserName;
    private String FirstName;
    private String LastName;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
