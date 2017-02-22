package com.uaeemployee.Network.ResponseDTOs;


import java.io.Serializable;

public class VacanciesDTO implements Serializable {


    private int VacancyID;
    private String JobType;
    private String Title;
    private String JobLevel;
    private String Description;
    private int SubSubOrganizationID;


    public VacanciesDTO() {
    }

    public VacanciesDTO(int vacancyID, String jobType, String title, String jobLevel, String description, int subSubOrganizationID) {
        VacancyID = vacancyID;
        JobType = jobType;
        Title = title;
        JobLevel = jobLevel;
        Description = description;
        SubSubOrganizationID = subSubOrganizationID;
    }

    public int getVacancyID() {
        return VacancyID;
    }

    public void setVacancyID(int vacancyID) {
        VacancyID = vacancyID;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getJobLevel() {
        return JobLevel;
    }

    public void setJobLevel(String jobLevel) {
        JobLevel = jobLevel;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getSubSubOrganizationID() {
        return SubSubOrganizationID;
    }

    public void setSubSubOrganizationID(int subSubOrganizationID) {
        SubSubOrganizationID = subSubOrganizationID;
    }
}