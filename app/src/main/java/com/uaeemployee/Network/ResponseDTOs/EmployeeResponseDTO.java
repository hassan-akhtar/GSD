package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 2/22/2017.
 * General Data
 */

public class EmployeeResponseDTO  extends ResponseDTO {

    private List<EmployeeDTO> employeeDTO;

    public List<EmployeeDTO> getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(List<EmployeeDTO> employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
}
