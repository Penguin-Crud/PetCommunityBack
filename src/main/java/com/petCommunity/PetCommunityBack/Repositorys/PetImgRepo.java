package com.petCommunity.PetCommunityBack.Repositorys;

import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetImgRepo extends JpaRepository<PetImg,Long> {

    @Query("select p from PetImg p where p.pet = ?1")
    List<PetImg>findAllByPet(Pet pet);
}
