package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.Mappers.AssociationMapper;
import com.petCommunity.PetCommunityBack.Repositorys.AssociationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.*;


@Service
public class AssociationCrudServ {

    @Autowired
    private AssociationRepo associationRepo;

    public AssociationCrudServ(AssociationRepo assocciationRepo) {
        this.associationRepo = assocciationRepo;
    }


    public AssociationRespDTO save(AssociationReqDTO associationReqDTO) {
        Association associationToSave = mapToAssociation(associationReqDTO);
        var dbResp = associationRepo.save(associationToSave);
        var reqResp = mapToAssociationRespDTO(dbResp);
        return reqResp;
    }


    public AssociationRespDTO getById(Long id) {
        return mapToAssociationRespDTO(associationRepo.findById(id).get());
    }


    public AssociationRespDTO update(AssociationReqDTO associationReqDTO) {
        Association associationToUpdate = mapToAssociation(associationReqDTO);
        var dbResp = associationRepo.save(associationToUpdate);
        var reqResp = mapToAssociationRespDTO(dbResp);
        return reqResp;
    }


    public List<AssociationRespDTO> getAll() {
        List<Association> dbAssociations = associationRepo.findAll();
        return dbAssociations.stream().map(AssociationMapper::mapToAssociationRespDTO)
                .collect(Collectors.toList());
    }


    public String deleteId(Long id) {
        associationRepo.deleteById(id);
        return associationRepo.existsById(id)?"Error":"Pet errased correctly.";
    }
}
