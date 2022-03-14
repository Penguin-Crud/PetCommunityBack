package com.petCommunity.PetCommunityBack.Controllers;


import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin
public class PetController {
    @Autowired
    private PetCrudService petCrudService;

    @GetMapping
    public List<Pet> getAll(){return petCrudService.getAll();}

    @GetMapping("/{id}")
    public PetRespDTO getById(@PathVariable Long id){
        return petCrudService.getById(id);
    }

    @PostMapping
    public Pet save(@RequestBody PetReqDTO pet){
        return petCrudService.save(pet);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        petCrudService.deleteId(id); }

    @PutMapping
    public Pet updateByID(@RequestBody PetReqDTO pet){
        var updatedAnimal = petCrudService.update(pet);

        return updatedAnimal;
    }

}
