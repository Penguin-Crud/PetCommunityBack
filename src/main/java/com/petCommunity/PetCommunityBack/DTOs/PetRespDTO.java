package com.petCommunity.PetCommunityBack.DTOs;

import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import lombok.*;

import java.util.List;


@Getter @Builder
public class PetRespDTO {
    public Long id;
    public String name;
    public Boolean hasChip;
    public String race;
    public String size;
    public String age;
    public String specie;
    public Boolean vaccinated;
    public String description;
    public UserRespDTO userRespDTO;
    public List<PetImg> petImg;


}
