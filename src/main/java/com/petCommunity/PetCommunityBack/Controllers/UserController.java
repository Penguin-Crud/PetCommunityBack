package com.petCommunity.PetCommunityBack.Controllers;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
import com.petCommunity.PetCommunityBack.Services.CloudinaryCloudStorageServiceImpl;
import com.petCommunity.PetCommunityBack.Services.IUserCrudServ;
import com.petCommunity.PetCommunityBack.auth.facade.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.mapToUser;
import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.mapToUserReqDTO;

@RestController
@RequestMapping("/associations")
@CrossOrigin
public class UserController {

    private final IUserCrudServ userCrudServ;

    private final CloudinaryCloudStorageServiceImpl cloudinaryImpl;

    @Autowired
    public UserController(IUserCrudServ userCrudServ, IAuthenticationFacade authenticationFacade, CloudinaryCloudStorageServiceImpl cloudinaryImpl){
        this.userCrudServ = userCrudServ;
        this.cloudinaryImpl = cloudinaryImpl;
    }

    @GetMapping
    public List<UserRespDTO> getAll(){return userCrudServ.getAll();}

    @GetMapping("/{id}")
    public UserRespDTO getById(@PathVariable Long id){
        return userCrudServ.getById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping (consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public UserRespDTO update(@RequestPart UserReqDTO user, @Nullable @RequestParam MultipartFile image) throws IOException {
        var userToSave = user;


        if(image != null) {
            var cloudinaryImgUrl = cloudinaryImpl.saveInCloud(image);
            userToSave.logo = cloudinaryImgUrl;
        }

        return userCrudServ.update(userToSave);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return userCrudServ.deleteId(id);
    }

    @PutMapping
    public UserRespDTO updateByID(@RequestBody UserReqDTO userReqDTO){
        var updatedUser = userCrudServ.update(userReqDTO);

        return updatedUser;
    }

}
