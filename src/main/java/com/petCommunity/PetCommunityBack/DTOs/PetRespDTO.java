package com.petCommunity.PetCommunityBack.DTOs;

import lombok.*;


@Getter @Builder
public class PetRespDTO {
    public Long id;
    public Boolean hasChip;
    public String race;
    public String size;
    public String age;
    public String specie;
    public Boolean vaccinated;
    public String description;
    public AssociationRespDTO associationRespDTO;


}
