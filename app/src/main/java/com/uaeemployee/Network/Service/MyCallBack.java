package com.uaeemployee.Network.Service;


import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;

/**
 * Created by hakhtar on 5/4/2016.
 */
public interface MyCallBack {

    void onSuccess(ResponseDTO responseDTO);

    void onFailure(ResponseDTO errorDTO);


}
