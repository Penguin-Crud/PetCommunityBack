package com.petCommunity.PetCommunityBack.DTOs;

import com.petCommunity.PetCommunityBack.DomainModels.Pet;
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
    public Pet.Sizes size;
    public String age;
    public Pet.Gender gender;
    public Pet.Priority priority;
    public Pet.Specie specie;
    public Boolean vaccines;
    public String description;
    public UserReqDTO userReqDTO;
}
