package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Mappers.PetMapper;
import com.petCommunity.PetCommunityBack.Repositorys.AssociationRepo;
import com.petCommunity.PetCommunityBack.Repositorys.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCrudService  {

    @Autowired
    private PetMapper petMapper;
    @Autowired
    private PetRepo petRepo;


    public Pet save(PetReqDTO pet){
        Pet petToSave = petMapper.mapToPet(pet);
        return petRepo.save(petToSave);
    }


    public PetRespDTO getById(Long id) {
        var dbPet = petRepo.findById(id);
        var petToMap = dbPet.get();
        var petRespDTO = petMapper.mapToPetDTO(petToMap);
        return petRespDTO;
    }


    public Pet update(PetReqDTO pet) {
        Pet petToUpdate = petMapper.mapToPet(pet);
        return petRepo.save(petToUpdate);
    }


    public List<Pet> getAll() {

        return petRepo.findAll();
    }


    public void deleteId(Long id) {
        petRepo.deleteById(id);
    }
}
