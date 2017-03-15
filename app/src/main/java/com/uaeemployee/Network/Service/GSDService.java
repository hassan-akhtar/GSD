package com.uaeemployee.Network.Service;

import com.uaeemployee.Network.RequestDTOs.GenderRequestDTO;
import com.uaeemployee.Network.RequestDTOs.LoginDTO;
import com.uaeemployee.Network.RequestDTOs.OrganizationsDTO;
import com.uaeemployee.Network.RequestDTOs.VacanciesDTO;

public interface GSDService {

     void loginRequest(LoginDTO loginFteDTO, MyCallBack callback);
     void getOrganizations(OrganizationsDTO organizationsDTO, MyCallBack callback);
     void getVacancies(VacanciesDTO organizationsDTO, MyCallBack callback);
     void getEmployees(VacanciesDTO organizationsDTO, MyCallBack callback);
     void getEmployeeDocument(OrganizationsDTO organizationsDTO, MyCallBack callback);
     void GetCountByGender(GenderRequestDTO genderRequestDTO, MyCallBack callback);

}
