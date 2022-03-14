package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.Repositorys.AssociationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociationCrudServ {

    private final AssociationRepo assocciationRepo;

    public AssociationCrudServ(AssociationRepo assocciationRepo) {
        this.assocciationRepo = assocciationRepo;
    }


    public Association save(Association association) {
        return assocciationRepo.save(association);
    }


    public Association getById(Long id) {
        return assocciationRepo.findById(id).get();
    }


    public Association update(Association association, Long id) {
        if(!assocciationRepo.existsById(id)) return null;
        association.setId(id);
        return assocciationRepo.save(association);
    }


    public List<Association> getAll() {
        return assocciationRepo.findAll();
    }


    public void deleteId(Long id) {
        assocciationRepo.deleteById(id);
    }
}
