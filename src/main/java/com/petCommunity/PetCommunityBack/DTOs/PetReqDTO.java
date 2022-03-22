package com.petCommunity.PetCommunityBack.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PetReqDTO {
    public Long id;
    public String name;
    public Boolean hasChip;
    public String race;
    public String size;
    public String age;
    public String specie;
    public Boolean vaccinated;
    public String description;
    public UserReqDTO userReqDTO;
}
