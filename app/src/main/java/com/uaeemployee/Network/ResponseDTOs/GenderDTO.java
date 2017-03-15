package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

public class GenderDTO {

    private int Count;
    private String Gen;
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
}
