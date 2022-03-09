package com.petCommunity.PetCommunityBack.Services;

import com.petCommunity.PetCommunityBack.DomainModels.Animal;
import com.petCommunity.PetCommunityBack.Repositorys.AnimalsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalsCrudService implements GenericCrudService<Animal,Integer> {

    private final AnimalsRepo animalsRepo;

    public AnimalsCrudService(AnimalsRepo animalsRepo) {
        this.animalsRepo = animalsRepo;
    }

    @Override
    public Animal save(Animal animal){
        return animalsRepo.save(animal);
    }

    @Override
    public Animal getById(Integer id) {
        return animalsRepo.findById(id).get();
    }

    @Override
    public Animal update(Animal animal, Integer id) {
        if(!animalsRepo.existsById(id)) return null;
        animal.setId(id);
        return animalsRepo.save(animal);
    }

    @Override
    public List<Animal> getAll() {
        return animalsRepo.findAll();
    }

    @Override
    public void deleteId(Integer id) {
        animalsRepo.deleteById(id);
    }
}
