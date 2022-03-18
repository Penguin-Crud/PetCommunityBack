package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;

public class AssociationMapper {
    public static AssociationRespDTO mapToAssociationRespDTO(Association association){
        AssociationRespDTO associationRespDTO = AssociationRespDTO.builder()
                .id(association.getId())
                .name(association.getName())
                .logo(association.getLogo())
                .adress(association.getAdress())
                .capacity(association.getCapacity())
                .build();
        return associationRespDTO;
    }
    public static AssociationReqDTO mapToAssociationReqDTO(Association association){
        AssociationReqDTO associationReqDTO = AssociationReqDTO.builder()
                .id(association.getId())
                .name(association.getName())
                .logo(association.getLogo())
                .adress(association.getAdress())
                .capacity(association.getCapacity())
                .build();
        return associationReqDTO;
    }

    public static Association mapToAssociation(AssociationReqDTO associationReqDTO){
        Association association = Association.builder()
                .id(associationReqDTO.getId())
                .name(associationReqDTO.getName())
                .logo(associationReqDTO.getLogo())
                .adress(associationReqDTO.getAdress())
                .capacity(associationReqDTO.getCapacity())
                .build();
        return association;
    }

    public static Association mapToAssociation(AssociationRespDTO associationRespDTO){
        Association association = Association.builder()
                .id(associationRespDTO.getId())
                .name(associationRespDTO.getName())
                .logo(associationRespDTO.getLogo())
                .adress(associationRespDTO.getAdress())
                .capacity(associationRespDTO.getCapacity())
                .build();
        return association;
    }

}
