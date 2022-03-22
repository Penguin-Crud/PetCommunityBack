package com.petCommunity.PetCommunityBack.DTOs;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserRespDTO {
    public Long id;
    public String name;
    public String logo;
    public String adress;
    public Integer capacity;
}
