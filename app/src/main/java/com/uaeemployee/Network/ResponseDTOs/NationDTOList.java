package com.uaeemployee.Network.ResponseDTOs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hakhtar on 3/19/2017.
 * General Data
 */

public class NationDTOList implements Serializable {

    private List<NationDTO> nationDTO;

    public NationDTOList(List<NationDTO> nationDTO) {
        this.nationDTO = nationDTO;
    }

    public List<NationDTO> getNationDTO() {
        return nationDTO;
    }

    public void setNationDTO(List<NationDTO> nationDTO) {
        this.nationDTO = nationDTO;
    }
}
