package com.petCommunity.PetCommunityBack.Services;


import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import com.petCommunity.PetCommunityBack.Repositorys.PetImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PetImgCrudService implements IPetImgCrudService{
    @Autowired PetImgRepo petImgRepo;

    public List<PetImg> save(Pet pet,String cloudImgUrl){
        PetImg petImgToSave = PetImg.builder()
                .url(cloudImgUrl)
                .pet(pet)
                .build();

        petImgRepo.save(petImgToSave);
        var resp = petImgRepo.findAllByPet(pet);
        return resp;
    }

    public List<PetImg> findAllByPet(Pet pet){
        return petImgRepo.findAllByPet(pet);
    }

    public String deleteAllImgsByPet(Pet pet){
        var imgsToDelete = findAllByPet(pet);
        petImgRepo.deleteAll(imgsToDelete);
        var petImgs = findAllByPet(pet);
        return petImgs.isEmpty()?"Pet Images deleted correctly":"Error";
    }

    public String deleteId(Long id) {
        petImgRepo.deleteById(id);
        return petImgRepo.existsById(id)?"Error":"Image errased correctly.";
    }

}
