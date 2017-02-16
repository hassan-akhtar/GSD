package com.uaeemployee.Network.ResponseDTOs;

import java.util.List;

/**
 * Created by hakhtar on 2/16/2017.
 * General Data
 */

public class OrganizationsResponseDTO extends ResponseDTO {

    private List<OrganizationsDTO> organizationsDTO;


    public List<OrganizationsDTO> getOrganizationsDTO() {
        return organizationsDTO;
    }

    public void setOrganizationsDTO(List<OrganizationsDTO> organizationsDTO) {
        this.organizationsDTO = organizationsDTO;
    }
}
