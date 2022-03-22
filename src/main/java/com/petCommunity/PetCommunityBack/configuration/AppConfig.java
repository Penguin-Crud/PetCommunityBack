package com.petCommunity.PetCommunityBack.configuration;

import com.petCommunity.PetCommunityBack.Services.IPetCrudService;
import com.petCommunity.PetCommunityBack.Services.ImgsStorageService;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ImgsStorageService cloudinaryImpl(){
        return new ImgsStorageService();
    }

}
