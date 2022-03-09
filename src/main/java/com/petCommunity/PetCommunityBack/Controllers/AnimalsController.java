package com.petCommunity.PetCommunityBack.Controllers;


import com.petCommunity.PetCommunityBack.DomainModels.Animal;
import com.petCommunity.PetCommunityBack.Services.AnimalsCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin
public class AnimalsController {
    @Autowired
    private AnimalsCrudService animalsCrudService;

    @GetMapping
    public List<Animal> getAll(){return animalsCrudService.getAll();}

    @GetMapping("/{id}")
    public Animal getById(@PathVariable int id){
        return animalsCrudService.getById(id);
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal){
        return animalsCrudService.save(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {animalsCrudService.deleteId(id); }

    @PutMapping("/{prodId}")
    public Animal updateByID(
            @PathVariable("prodId") int id,
            @RequestBody Animal animal){

        var updatedAnimal = animalsCrudService.update(animal, id);

        return updatedAnimal;
    }

}
