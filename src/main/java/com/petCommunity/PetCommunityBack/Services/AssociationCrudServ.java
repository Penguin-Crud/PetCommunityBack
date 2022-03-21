package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.User;
import com.petCommunity.PetCommunityBack.Mappers.AssociationMapper;
import com.petCommunity.PetCommunityBack.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.*;


@Service
public class AssociationCrudServ implements IAssociationCrudServ{

    @Autowired
    private UserRepository userRepository;

    public AssociationCrudServ(UserRepository assocciationRepo) {
        this.userRepository = assocciationRepo;
    }


    public AssociationRespDTO save(AssociationReqDTO associationReqDTO) {
        User userToSave = mapToAssociation(associationReqDTO);
        var dbResp = userRepository.save(userToSave);
        var reqResp = mapToAssociationRespDTO(dbResp);
        return reqResp;
    }


    public AssociationRespDTO getById(Long id) {
        return mapToAssociationRespDTO(userRepository.findById(id).get());
    }


    public AssociationRespDTO update(AssociationReqDTO associationReqDTO) {
        User userToUpdate = mapToAssociation(associationReqDTO);
        var dbResp = userRepository.save(userToUpdate);
        var reqResp = mapToAssociationRespDTO(dbResp);
        return reqResp;
    }


    public List<AssociationRespDTO> getAll() {
        List<User> dbUsers = userRepository.findAll();
        return dbUsers.stream().map(AssociationMapper::mapToAssociationRespDTO)
                .collect(Collectors.toList());
    }


    public String deleteId(Long id) {
        userRepository.deleteById(id);
        return userRepository.existsById(id)?"Error":"Association errased correctly.";
    }
}
