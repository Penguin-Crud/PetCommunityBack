package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToAssociation;
import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToAssociationRespDTO;


@Component
public class PetMapper {



    public static PetRespDTO mapToPetRespDTO(@NotNull Pet pet){
        PetRespDTO petRespDTO = PetRespDTO.builder()
                .id(pet.getId())
                .hasChip(pet.getHasChip())
                .race(pet.getRace())
                .size(pet.getSize())
                .age(pet.getAge())
                .specie(pet.getSpecie())
                .vaccinated(pet.getVaccinated())
                .description(pet.getDescription())
                .associationRespDTO(mapToAssociationRespDTO(pet.getAssociation()))
                .build();
        return petRespDTO;
    }
    public static Pet mapToPet(@NotNull PetReqDTO petDTO){
        Pet pet = Pet.builder()
                .name(petDTO.name)
                .hasChip(petDTO.hasChip)
                .race(petDTO.race)
                .size(petDTO.size)
                .age(petDTO.age)
                .specie(petDTO.specie)
                .vaccinated(petDTO.vaccinated)
                .description(petDTO.description)
                .association(mapToAssociation(petDTO.associationReqDTO))
                .build();
        if(petDTO.id!=null)pet.setId(petDTO.id);

        return pet;
    }



}
