package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;

import java.util.List;

public interface IUserCrudServ {

    UserRespDTO getById(Long id);
    UserRespDTO update(UserReqDTO userReqDTO);
    List<UserRespDTO> getAll();
    String deleteId(Long id);
}
