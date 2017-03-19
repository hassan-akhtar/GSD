package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

public class GenderDTO {

    private int Count;
    private String Gen;
    private int Salary;
    List<NationDTO> lstNation;


    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getGen() {
        return Gen;
    }

    public void setGen(String gen) {
        Gen = gen;
    }

    public List<NationDTO> getLstNation() {
        return lstNation;
    }

    public void setLstNation(List<NationDTO> lstNation) {
        this.lstNation = lstNation;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }
}
