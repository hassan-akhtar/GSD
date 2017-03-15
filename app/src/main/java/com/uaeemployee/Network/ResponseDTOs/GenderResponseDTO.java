package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 3/8/2017.
 * General Data
 */

public class GenderResponseDTO extends ResponseDTO{

    private List<GenderDTO> genderDTO;

    public List<GenderDTO> getGenderDTO() {
        return genderDTO;
    }

    public void setGenderDTO(List<GenderDTO> genderDTO) {
        this.genderDTO = genderDTO;
    }
}
