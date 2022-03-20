package com.petCommunity.PetCommunityBack.Controllers;


import com.cloudinary.Cloudinary;
import com.petCommunity.PetCommunityBack.AuthUser;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.Services.IPetCrudService;
import com.petCommunity.PetCommunityBack.Services.ImgsStorageService;
import com.petCommunity.PetCommunityBack.Services.PetImgCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToAssociationReqDTO;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/pets")
@CrossOrigin
public class PetController {
    @Autowired
    IPetCrudService petCrudService;
    @Autowired
    ImgsStorageService cloudinaryImpl;

    @Autowired AuthUser authUser;

    @GetMapping
    public List<PetRespDTO> getAll(){
        return petCrudService.getAll();
    }

    @GetMapping("/{id}")
    public PetRespDTO getById(@PathVariable Long id){
        return petCrudService.getById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public PetRespDTO save(@RequestPart PetReqDTO pet,@RequestParam MultipartFile image) throws IOException {
        var cloudinaryImgUrl = cloudinaryImpl.saveInCloudinary(image);
        pet.setAssociationReqDTO(mapToAssociationReqDTO(authUser.user));


        return petCrudService.save(pet, cloudinaryImgUrl);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return petCrudService.deleteId(id);
    }

    @PutMapping
    public PetRespDTO updateByID(@RequestBody PetReqDTO pet){
        var updatedAnimal = petCrudService.update(pet);

        return updatedAnimal;
    }

}
