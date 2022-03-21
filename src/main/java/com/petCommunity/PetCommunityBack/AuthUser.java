package com.petCommunity.PetCommunityBack;

import com.petCommunity.PetCommunityBack.DomainModels.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component @NoArgsConstructor
public class AuthUser {
    public User user = User.builder()
            .id(1L)
            .username("patitas")
            .adress("street 123")
            .logo("logo.jpg")
            .password("asdfr4321")
            .capacity(50)
            .build();
}
