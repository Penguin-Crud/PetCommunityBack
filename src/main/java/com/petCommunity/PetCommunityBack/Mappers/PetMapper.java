package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Services.PetImgCrudService;
import com.petCommunity.PetCommunityBack.auth.facade.AuthenticationFacade;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToUser;
import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToUserRespDTO;


@Component
public class PetMapper {

    private static PetImgCrudService petImgCrudService;

    private static AuthenticationFacade authenticationFacade;

    @Autowired
    public void setPetImgCrudService(PetImgCrudService petImgCrudService){
        PetMapper.petImgCrudService=petImgCrudService;
    }
    @Autowired
    public void setAuthenticationFacade(AuthenticationFacade authenticationFacade){
        PetMapper.authenticationFacade=authenticationFacade;
    }

     public static PetRespDTO mapToPetRespDTO(@NotNull Pet pet){
        PetRespDTO petRespDTO = PetRespDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .chip(pet.getChip())
                .race(pet.getRace())
                .size(pet.getSize())
                .age(pet.getAge())
                .specie(pet.getSpecie())
                .vaccines(pet.getVaccines())
                .description(pet.getDescription())
                .userRespDTO(mapToUserRespDTO(pet.getUser()))
                .petImg(petImgCrudService.findAllByPet(pet))
                .build();
        return petRespDTO;
    }
    public static Pet mapToPet( PetReqDTO petDTO){
        Pet pet = Pet.builder()
                .name(petDTO.name)
                .chip(petDTO.chip)
                .race(petDTO.race)
                .size(petDTO.size)
                .age(petDTO.age)
                .specie(petDTO.specie)
                .vaccines(petDTO.vaccines)
                .description(petDTO.description)
                .user(authenticationFacade.getAuthUser())
                .build();
        if(petDTO.id!=null){pet.setId(petDTO.id);}

        return pet;
    }

    public static Pet mapToPet( PetRespDTO petDTO){
        Pet pet = Pet.builder()
                .name(petDTO.name)
                .chip(petDTO.chip)
                .race(petDTO.race)
                .size(petDTO.size)
                .age(petDTO.age)
                .specie(petDTO.specie)
                .vaccines(petDTO.vaccines)
                .description(petDTO.description)
                .user(mapToUser(petDTO.userRespDTO))
                .build();
        if(petDTO.id!=null){pet.setId(petDTO.id);}

        return pet;
    }



}
