package com.petCommunity.PetCommunityBack.Repositorys;

import com.petCommunity.PetCommunityBack.DomainModels.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepo extends JpaRepository<Animal,Integer> {
}
