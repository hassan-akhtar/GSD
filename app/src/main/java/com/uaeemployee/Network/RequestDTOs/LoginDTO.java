package com.uaeemployee.Network.RequestDTOs;

/**
 * Created by hakhtar on 2/14/2017.
 * General Data
 */

public class LoginDTO extends BaseDTO {

    private String username;
    private String password;


    public LoginDTO(int callBackId, String username, String password) {
        super(callBackId);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
