package com.uaeemployee.Network.Service;


import com.uaeemployee.Network.ResponseDTOs.LoginResponseDTO;
import com.uaeemployee.Utils.SystemConstants;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GSDRESTService {


    // Login User Request FTE
    @GET("/{username}/{password}")
     void loginRequest(@Path("username")  String user, @Path("password")  String password,
                             Callback<LoginResponseDTO> loginResponseDTOCallback);


}

