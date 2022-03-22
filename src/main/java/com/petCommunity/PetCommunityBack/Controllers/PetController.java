package com.petCommunity.PetCommunityBack.Controllers;


import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.Services.IPetCrudService;
import com.petCommunity.PetCommunityBack.Services.ImgsStorageService;
import com.petCommunity.PetCommunityBack.Services.PetImgCrudService;
import com.petCommunity.PetCommunityBack.auth.facade.IAuthenticationFacade;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToUserReqDTO;

@RestController
@RequestMapping("/pets")
@CrossOrigin @Setter
public class PetController {

    private final IPetCrudService petCrudService;
    private final PetImgCrudService petImgCrudService;
    private final ImgsStorageService cloudinaryImpl;
    private final IAuthenticationFacade authenticationFacade;

    @Autowired
    public PetController(IPetCrudService petCrudService, PetImgCrudService petImgCrudService, ImgsStorageService cloudinaryImpl, IAuthenticationFacade authenticationFacade) {
        this.petCrudService = petCrudService;
        this.petImgCrudService = petImgCrudService;
        this.cloudinaryImpl = cloudinaryImpl;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public List<PetRespDTO> getAll(){
        return petCrudService.getAll();
    }

    @GetMapping("/{id}")
    public PetRespDTO getById(@PathVariable Long id){
        return petCrudService.getPetRespById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public PetRespDTO save(@RequestPart PetReqDTO pet,@RequestParam MultipartFile image) throws IOException {
        var user = authenticationFacade.getAuthUser();
        var cloudinaryImgUrl = cloudinaryImpl.saveInCloudinary(image);
        pet.setUserReqDTO(mapToUserReqDTO(user));


        return petCrudService.save(pet, cloudinaryImgUrl);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        var petToDelete = petCrudService.getPetById(id);
        petImgCrudService.deleteAllImgsByPet(petToDelete);
        return petCrudService.deleteId(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public PetRespDTO updateByID(@RequestBody PetReqDTO pet){
        var updatedAnimal = petCrudService.update(pet);

        return updatedAnimal;
    }

}
