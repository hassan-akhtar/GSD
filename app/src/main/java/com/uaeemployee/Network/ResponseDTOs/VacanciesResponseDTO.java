package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 2/22/2017.
 * General Data
 */

public class VacanciesResponseDTO extends ResponseDTO {

    private List<VacanciesDTO> vacanciesDTO;

    public List<VacanciesDTO> getVacanciesDTO() {
        return vacanciesDTO;
    }

    public void setVacanciesDTO(List<VacanciesDTO> vacanciesDTO) {
        this.vacanciesDTO = vacanciesDTO;
    }
}
