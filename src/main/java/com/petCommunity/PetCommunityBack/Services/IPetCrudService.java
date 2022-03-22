package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;

import java.util.List;

public interface IPetCrudService {
    PetRespDTO save(PetReqDTO pet, String url);
    PetRespDTO getPetRespById(Long id);
    Pet getPetById(Long id);
    PetRespDTO update(PetReqDTO pet);
    List<PetRespDTO> getAll();
    String deleteId(Long id);
}
