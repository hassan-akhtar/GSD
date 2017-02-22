package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 2/22/2017.
 * General Data
 */

public class EmployeeDocumentResponseDTO extends ResponseDTO {

    private List<EmployeeDocument> employeeDocument;

    public List<EmployeeDocument> getEmployeeDocument() {
        return employeeDocument;
    }

    public void setEmployeeDocument(List<EmployeeDocument> employeeDocument) {
        this.employeeDocument = employeeDocument;
    }
}
