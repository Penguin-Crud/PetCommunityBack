package com.petCommunity.PetCommunityBack.configuration;

import com.petCommunity.PetCommunityBack.Services.CloudinaryCloudStorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CloudinaryCloudStorageServiceImpl cloudinaryImpl(){
        return new CloudinaryCloudStorageServiceImpl();
    }

}
