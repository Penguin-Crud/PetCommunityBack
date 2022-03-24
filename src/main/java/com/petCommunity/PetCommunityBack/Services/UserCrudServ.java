package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.User;
import com.petCommunity.PetCommunityBack.Mappers.UserMapper;
import com.petCommunity.PetCommunityBack.Repositorys.UserRepository;
import com.petCommunity.PetCommunityBack.auth.facade.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.*;


@Service
public class UserCrudServ implements IUserCrudServ {


    private final UserRepository userRepository;
    private final IAuthenticationFacade authenticationFacade;

    @Autowired
    public UserCrudServ(UserRepository userRepo, IAuthenticationFacade authenticationFacade) {
        this.userRepository = userRepo;
        this.authenticationFacade = authenticationFacade;
    }


    public UserRespDTO update(UserReqDTO userReqDTO) {
        User userDataRequest = mapToUser(userReqDTO);
        var authenticatedUserId = authenticationFacade.getAuthUser().getId();

        var userToUpdate = userRepository.findById(authenticatedUserId).get();

        if(userDataRequest.getUsername() != userToUpdate.getUsername()){userToUpdate.setUsername(userDataRequest.getUsername());}
        if(userDataRequest.getLogo() != null) {
            if (userDataRequest.getLogo() != userToUpdate.getLogo()) {
                userToUpdate.setLogo(userDataRequest.getLogo());
            }
        }
        if(userDataRequest.getAdress() != userToUpdate.getAdress()){userToUpdate.setAdress(userDataRequest.getAdress());}
        if(userDataRequest.getCapacity() != userToUpdate.getCapacity()){userToUpdate.setCapacity(userDataRequest.getCapacity());}

        var dbResp = userRepository.save(userToUpdate);
        var reqResp = mapToUserRespDTO(dbResp);
        return reqResp;
    }


    public UserRespDTO getById(Long id) {
        return mapToUserRespDTO(userRepository.findById(id).get());
    }

    public List<UserRespDTO> getAll() {
        List<User> dbUsers = userRepository.findAll();
        return dbUsers.stream().map(UserMapper::mapToUserRespDTO)
                .collect(Collectors.toList());
    }

    public String deleteId(Long id) {
        userRepository.deleteById(id);
        return userRepository.existsById(id)?"Error":"User errased correctly.";
    }
}
