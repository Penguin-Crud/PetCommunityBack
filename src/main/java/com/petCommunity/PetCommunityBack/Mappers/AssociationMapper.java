package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.User;

public class AssociationMapper {
    public static AssociationRespDTO mapToAssociationRespDTO(User user){
        AssociationRespDTO associationRespDTO = AssociationRespDTO.builder()
                .id(user.getId())
                .name(user.getUsername())
                .logo(user.getLogo())
                .adress(user.getAdress())
                .capacity(user.getCapacity())
                .build();
        return associationRespDTO;
    }
    public static AssociationReqDTO mapToAssociationReqDTO(User user){
        AssociationReqDTO associationReqDTO = AssociationReqDTO.builder()
                .id(user.getId())
                .name(user.getUsername())
                .logo(user.getLogo())
                .adress(user.getAdress())
                .capacity(user.getCapacity())
                .build();
        return associationReqDTO;
    }

    public static User mapToAssociation(AssociationReqDTO associationReqDTO){
        User user = User.builder()
                .id(associationReqDTO.getId())
                .username(associationReqDTO.getName())
                .logo(associationReqDTO.getLogo())
                .adress(associationReqDTO.getAdress())
                .capacity(associationReqDTO.getCapacity())
                .build();
        return user;
    }

    public static User mapToAssociation(AssociationRespDTO associationRespDTO){
        User user = User.builder()
                .id(associationRespDTO.getId())
                .username(associationRespDTO.getName())
                .logo(associationRespDTO.getLogo())
                .adress(associationRespDTO.getAdress())
                .capacity(associationRespDTO.getCapacity())
                .build();
        return user;
    }

}
