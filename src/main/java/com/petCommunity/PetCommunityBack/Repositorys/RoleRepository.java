package com.petCommunity.PetCommunityBack.Repositorys;


import com.petCommunity.PetCommunityBack.DomainModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Role.RoleName roleName);
}
