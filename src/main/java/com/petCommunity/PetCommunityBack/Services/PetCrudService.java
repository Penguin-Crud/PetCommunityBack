package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Mappers.PetMapper;
import com.petCommunity.PetCommunityBack.Repositorys.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.petCommunity.PetCommunityBack.Mappers.PetMapper.*;


@Service
public class PetCrudService implements IPetCrudService {

    @Autowired PetRepo petRepo;
    @Autowired PetImgCrudService petImgCrudService;

    public PetRespDTO save(PetReqDTO pet, String cloudinaryImgUrl){
        Pet petToSave = mapToPet(pet);
        var dbResp = petRepo.save(petToSave);
        petImgCrudService.save(dbResp,cloudinaryImgUrl);
        var reqResp = mapToPetRespDTO(dbResp);
        return reqResp;
    }


    public PetRespDTO getPetRespById(Long id) {
        var dbPet = petRepo.findById(id);
        var petToMap = dbPet.get();
        return mapToPetRespDTO(petToMap);
    }

    public Pet getPetById(Long id) {
        var dbPet = petRepo.findById(id).get();

        return dbPet;
    }


    public PetRespDTO update(PetReqDTO pet) {
        Pet petToUpdate = mapToPet(pet);
        var dbResp = petRepo.save(petToUpdate);
        var reqResp = mapToPetRespDTO(dbResp);
        return reqResp;
    }


    public List<PetRespDTO> getAll() {
        List<Pet> dbPets = petRepo.findAll();
        return dbPets.stream().map(PetMapper::mapToPetRespDTO)
                .collect(Collectors.toList());
    }


    public String deleteId(Long id) {

        petRepo.deleteById(id);

        return petRepo.existsById(id)?"Error":"Pet errased correctly.";
    }
}
