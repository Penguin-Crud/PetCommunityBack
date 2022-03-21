package com.petCommunity.PetCommunityBack.auth.facade;


import com.petCommunity.PetCommunityBack.DomainModels.User;

public interface IAuthenticationFacade {
    public User getAuthUser();
}
