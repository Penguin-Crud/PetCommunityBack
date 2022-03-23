package com.petCommunity.PetCommunityBack.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudStorageService {

    String saveInCloud(MultipartFile file) throws IOException;
}
