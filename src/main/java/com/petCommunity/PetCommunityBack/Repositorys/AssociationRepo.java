package com.petCommunity.PetCommunityBack.Repositorys;

import com.petCommunity.PetCommunityBack.DomainModels.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepo extends JpaRepository<Association,Long> {
}
