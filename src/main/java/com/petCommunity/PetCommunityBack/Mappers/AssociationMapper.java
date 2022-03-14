package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;

public class AssociationMapper {
    static AssociationRespDTO mapToAssociationDTO(Association association){
        AssociationRespDTO associationRespDTO = AssociationRespDTO.builder()
                .id(association.getId())
                .name(association.getName())
                .logo(association.getLogo())
                .adress(association.getAdress())
                .capacity(association.getCapacity())
                .build();
        return associationRespDTO;
    }
}
