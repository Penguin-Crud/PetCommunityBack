package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.DomainModels.PetImg;

import java.util.List;

public interface IPetImgCrudService {

    List<PetImg> save(Pet pet, String cloudImgUrl);
    List<PetImg> findAllByPet(Pet pet);
    String deleteAllImgsByPet(Pet pet);
    String deleteId(Long id);
}
