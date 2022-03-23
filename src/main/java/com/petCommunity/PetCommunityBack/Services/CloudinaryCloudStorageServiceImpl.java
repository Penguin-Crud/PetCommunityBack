package com.petCommunity.PetCommunityBack.Services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service @Setter
public class CloudinaryCloudStorageServiceImpl implements ICloudStorageService{

   private final Map params= ObjectUtils.asMap(
            "cloud_name", "petimgstrg",
            "api_key", "738385571222694",
            "api_secret", "4JFmN_zZ2gs-ECXyVNZ2ItVe62o"
    );
    private Cloudinary imgStorage;

    public CloudinaryCloudStorageServiceImpl(){
        this.imgStorage = new Cloudinary(params);
    }

    public String saveInCloud(MultipartFile file) throws IOException {
        var fileToSave = file.getBytes();
        var uploadResp = imgStorage.uploader().upload(fileToSave,ObjectUtils.emptyMap());
        var savedFileUrl = uploadResp.get("url").toString();
        return savedFileUrl;
    }


}
