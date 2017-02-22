package com.uaeemployee.Network.ResponseDTOs;

/**
 * Created by hakhtar on 2/22/2017.
 * General Data
 */

public class EmployeeDocument {

    private int DocumentID;
    private int EmployeeID;
    private int DocumentTypeID;
    private String DocumentName;
    private String ExpiryDate;
    private String IssueDate;
    private String DocPath;

    public EmployeeDocument() {
    }

    public EmployeeDocument(int documentID, int employeeID, int documentTypeID, String documentName, String expiryDate, String issueDate, String docPath) {
        DocumentID = documentID;
        EmployeeID = employeeID;
        DocumentTypeID = documentTypeID;
        DocumentName = documentName;
        ExpiryDate = expiryDate;
        IssueDate = issueDate;
        DocPath = docPath;
    }

    public int getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(int documentID) {
        DocumentID = documentID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public int getDocumentTypeID() {
        return DocumentTypeID;
    }

    public void setDocumentTypeID(int documentTypeID) {
        DocumentTypeID = documentTypeID;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getDocPath() {
        return DocPath;
    }

    public void setDocPath(String docPath) {
        DocPath = docPath;
    }
}
