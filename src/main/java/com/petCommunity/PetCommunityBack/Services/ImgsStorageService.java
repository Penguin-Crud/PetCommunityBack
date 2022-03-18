package com.petCommunity.PetCommunityBack.Services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImgsStorageService {
    private final Cloudinary imgStorage = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "petimgstrg",
            "api_key", "738385571222694",
            "api_secret", "4JFmN_zZ2gs-ECXyVNZ2ItVe62o"
    ));

    public String saveInCloudinary(MultipartFile file) throws IOException {
        var fileToSave = file.getBytes();
        var uploadResp = imgStorage.uploader().upload(fileToSave,ObjectUtils.emptyMap());
        var savedFileUrl = uploadResp.get("url").toString();
        return savedFileUrl;
    }


}
