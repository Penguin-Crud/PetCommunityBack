package com.petCommunity.PetCommunityBack.Mappers;

import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class PetMapper {



    public PetRespDTO mapToPetDTO(@NotNull Pet pet){
        PetRespDTO petRespDTO = PetRespDTO.builder()
                .id(pet.getId())
                .hasChip(pet.getHasChip())
                .race(pet.getRace())
                .size(pet.getSize())
                .age(pet.getAge())
                .specie(pet.getSpecie())
                .vaccinated(pet.getVaccinated())
                .description(pet.getDescription())
                .association(AssociationMapper.mapToAssociationDTO(pet.getAssociation()))
                .build();
        return petRespDTO;
    }
    public Pet mapToPet(@NotNull PetReqDTO petDTO){
        Pet pet = Pet.builder()
                .name(petDTO.name)
                .hasChip(petDTO.hasChip)
                .race(petDTO.race)
                .size(petDTO.size)
                .age(petDTO.age)
                .specie(petDTO.specie)
                .vaccinated(petDTO.vaccinated)
                .description(petDTO.description)
                .build();
        if(petDTO.id!=null)pet.setId(petDTO.id);

        return pet;
    }



}
