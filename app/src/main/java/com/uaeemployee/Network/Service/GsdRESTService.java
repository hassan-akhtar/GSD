package com.uaeemployee.Network.Service;


import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDocument;
import com.uaeemployee.Network.ResponseDTOs.GenderDTO;
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
    @GET("/GetAllVacancies/null/{OrganizationID}/{StatusID}/{LoginEmpID}")
    void getVacancies( @Path("OrganizationID")  int OrganizationID, @Path("StatusID")  int StatusID,  @Path("LoginEmpID")  int LoginEmpID,
                          Callback<List<VacanciesDTO>> vacanciesResponseDTO);


    // get Employees Request
    @GET("/GetAllEmployees/null/{OrganizationID}/{StatusID}/{LoginEmpID}")
    void getAllEmployees(@Path("OrganizationID")  int OrganizationID, @Path("StatusID")  int StatusID,  @Path("LoginEmpID")  int LoginEmpID,
                      Callback<List<EmployeeDTO>> employeeResponseDTO);


    // get Employee Document Request
    @GET("/GetEmployeeDocuments/{id}")
    void getEmployeeDocument(@Path("id")  int user,
                         Callback<List<EmployeeDocument>> employeeDocument);

    // get count by gender
    @GET("/GetCountBySex/{OrganizationID}/{StatusID}/{LoginEmpID}")
    void getCountByGender(@Path("OrganizationID")  int OrganizationID, @Path("StatusID")  int StatusID, @Path("LoginEmpID")  int LoginEmpID,
                             Callback<List<GenderDTO>> genderResponseDTO);
}

