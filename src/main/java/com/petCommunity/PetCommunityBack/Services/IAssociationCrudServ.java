package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;

import java.util.List;

public interface IAssociationCrudServ {

    AssociationRespDTO save(AssociationReqDTO associationReqDTO);
    AssociationRespDTO getById(Long id);
    AssociationRespDTO update(AssociationReqDTO associationReqDTO);
    List<AssociationRespDTO> getAll();
    String deleteId(Long id);
}
