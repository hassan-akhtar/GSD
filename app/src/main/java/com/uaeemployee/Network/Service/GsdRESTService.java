package com.uaeemployee.Network.Service;


import com.uaeemployee.Network.ResponseDTOs.LoginResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GSDRESTService {


    // Login User Request
    @GET("/IsExistuserNamePassword/{username}/{password}")
     void loginRequest(@Path("username")  String user, @Path("password")  String password,
                             Callback<LoginResponseDTO> loginResponseDTOCallback);


    // get Organizations Request
    @GET("/GetAllOrganizations/{id}")
    void getOrganizations(@Path("id")  int user,
                      Callback<List<OrganizationsDTO>> organizationsDTOCallback);

    // get Vacancies Request
    @GET("/GetAllVacancies/null/{id}")
    void getVacancies(@Path("id")  int user,
                          Callback<List<VacanciesDTO>> vacanciesResponseDTO);

}

