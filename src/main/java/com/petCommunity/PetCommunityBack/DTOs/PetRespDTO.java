package com.petCommunity.PetCommunityBack.DTOs;

import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import lombok.*;

import java.util.List;


@Getter @Builder
public class PetRespDTO {
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
    public AssociationRespDTO associationRespDTO;
    public List<PetImg> imgURL;


}
