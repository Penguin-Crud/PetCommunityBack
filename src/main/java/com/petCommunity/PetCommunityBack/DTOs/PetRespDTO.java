package com.petCommunity.PetCommunityBack.DTOs;

import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;


@Builder
public class PetRespDTO {
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
    public UserRespDTO userRespDTO;
    public List<PetImg> petImg;
}
