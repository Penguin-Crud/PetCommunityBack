package com.petCommunity.PetCommunityBack;

import com.petCommunity.PetCommunityBack.DomainModels.Association;
import org.springframework.stereotype.Component;

@Component
public class AuthUser {
    public Association user = Association.builder()
            .id(1L)
            .name("patitas")
            .adress("street 123")
            .logo("logo.jpg")
            .password("asdfr4321")
            .capacity(50)
            .build();
}
