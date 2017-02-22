package com.uaeemployee.Network.Service;

import com.uaeemployee.Network.RequestDTOs.LoginDTO;
import com.uaeemployee.Network.RequestDTOs.OrganizationsDTO;

public interface GSDService {

     void loginRequest(LoginDTO loginFteDTO, MyCallBack callback);
     void getOrganizations(OrganizationsDTO organizationsDTO, MyCallBack callback);
     void getVacancies(OrganizationsDTO organizationsDTO, MyCallBack callback);
}
