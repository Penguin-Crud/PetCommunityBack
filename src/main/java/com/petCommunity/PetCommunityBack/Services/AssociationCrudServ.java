package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
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


    public UserRespDTO save(UserReqDTO userReqDTO) {
        User userToSave = mapToUser(userReqDTO);
        var dbResp = userRepository.save(userToSave);
        var reqResp = mapToUserRespDTO(dbResp);
        return reqResp;
    }


    public UserRespDTO getById(Long id) {
        return mapToUserRespDTO(userRepository.findById(id).get());
    }


    public UserRespDTO update(UserReqDTO userReqDTO) {
        User userToUpdate = mapToUser(userReqDTO);
        var dbResp = userRepository.save(userToUpdate);
        var reqResp = mapToUserRespDTO(dbResp);
        return reqResp;
    }


    public List<UserRespDTO> getAll() {
        List<User> dbUsers = userRepository.findAll();
        return dbUsers.stream().map(AssociationMapper::mapToUserRespDTO)
                .collect(Collectors.toList());
    }


    public String deleteId(Long id) {
        userRepository.deleteById(id);
        return userRepository.existsById(id)?"Error":"Association errased correctly.";
    }
}
