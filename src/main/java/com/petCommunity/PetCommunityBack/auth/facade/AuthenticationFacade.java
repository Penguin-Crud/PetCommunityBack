package com.petCommunity.PetCommunityBack.auth.facade;


import com.petCommunity.PetCommunityBack.DomainModels.User;
import com.petCommunity.PetCommunityBack.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Autowired
    UserRepository userRepository;

    public User getAuthUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(userName).get();
    }
}
