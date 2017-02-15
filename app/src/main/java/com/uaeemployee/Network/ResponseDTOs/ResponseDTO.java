package com.uaeemployee.Network.ResponseDTOs;


import com.uaeemployee.Network.RequestDTOs.BaseDTO;

/**
 * Created by hakhtar on 5/4/2016.
 */
public class ResponseDTO extends BaseDTO {

    public static final String SUCCESS = "000";
    private String status;
    private String message;
    private String code;


    public ResponseDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.getCode());
    }

}

