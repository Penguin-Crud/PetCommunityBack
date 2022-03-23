package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.User;

public class UserMapper {
    public static UserRespDTO mapToUserRespDTO(User user){
        UserRespDTO userRespDTO = UserRespDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .logo(user.getLogo())
                .adress(user.getAdress())
                .capacity(user.getCapacity())
                .build();
        return userRespDTO;
    }
    public static UserReqDTO mapToUserReqDTO(User user){
        UserReqDTO userReqDTO = UserReqDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .logo(user.getLogo())
                .adress(user.getAdress())
                .capacity(user.getCapacity())
                .build();
        return userReqDTO;
    }

    public static User mapToUser(UserReqDTO userReqDTO){
        User user = User.builder()
                .id(userReqDTO.getId())
                .username(userReqDTO.getUsername())
                .logo(userReqDTO.getLogo())
                .adress(userReqDTO.getAdress())
                .capacity(userReqDTO.getCapacity())
                .build();
        return user;
    }

    public static User mapToUser(UserRespDTO userRespDTO){
        User user = User.builder()
                .id(userRespDTO.getId())
                .username(userRespDTO.getUsername())
                .logo(userRespDTO.getLogo())
                .adress(userRespDTO.getAdress())
                .capacity(userRespDTO.getCapacity())
                .build();
        return user;
    }

}
