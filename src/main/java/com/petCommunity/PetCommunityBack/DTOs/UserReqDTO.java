package com.petCommunity.PetCommunityBack.DTOs;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserReqDTO {
    public Long id;
    public String username;
    public String logo;
    public String adress;
    public Integer capacity;
}
