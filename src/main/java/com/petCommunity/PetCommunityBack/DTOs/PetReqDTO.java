package com.petCommunity.PetCommunityBack.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PetReqDTO {
    public Long id;
    public String name;
    public Boolean chip;
    public String race;
    public String size;
    public String age;
    public String specie;
    public Boolean vaccines;
    public String gender;
    public String date;
    public String description;
    public AssociationReqDTO associationReqDTO;
}
