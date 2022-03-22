package com.petCommunity.PetCommunityBack.Controllers;

import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
import com.petCommunity.PetCommunityBack.Services.IAssociationCrudServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associations")
@CrossOrigin
public class AssociationController {
    @Autowired
    private IAssociationCrudServ associationCrudServ;

    @GetMapping
    public List<UserRespDTO> getAll(){return associationCrudServ.getAll();}

    @GetMapping("/{id}")
    public UserRespDTO getById(@PathVariable Long id){
        return associationCrudServ.getById(id);
    }

    @PostMapping
    public UserRespDTO save(@RequestBody UserReqDTO userReqDTO){
        return associationCrudServ.save(userReqDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return associationCrudServ.deleteId(id);
    }

    @PutMapping
    public UserRespDTO updateByID(@RequestBody UserReqDTO userReqDTO){
        var updatedAssociation = associationCrudServ.update(userReqDTO);

        return updatedAssociation;
    }
}
