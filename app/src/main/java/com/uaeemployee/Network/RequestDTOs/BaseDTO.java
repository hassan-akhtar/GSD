package com.uaeemployee.Network.RequestDTOs;

/**
 * Created by hakhtar on 5/4/2016.
 */
public class BaseDTO {

    private transient int callBackId;

    public BaseDTO() {
    }
    public BaseDTO(int callBackId) {
        this.callBackId = callBackId;
    }

    public int getCallBackId() {
        return callBackId;
    }

    public void setCallBackId(int callBackId) {
        this.callBackId = callBackId;
    }
}
