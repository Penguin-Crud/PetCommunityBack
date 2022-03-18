package com.petCommunity.PetCommunityBack.Controllers;

import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.Services.AssociationCrudServ;
import com.petCommunity.PetCommunityBack.Services.IAssociationCrudServ;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
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
    public List<AssociationRespDTO> getAll(){return associationCrudServ.getAll();}

    @GetMapping("/{id}")
    public AssociationRespDTO getById(@PathVariable Long id){
        return associationCrudServ.getById(id);
    }

    @PostMapping
    public AssociationRespDTO save(@RequestBody AssociationReqDTO associationReqDTO){
        return associationCrudServ.save(associationReqDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return associationCrudServ.deleteId(id);
    }

    @PutMapping
    public AssociationRespDTO updateByID(@RequestBody AssociationReqDTO associationReqDTO){
        var updatedAssociation = associationCrudServ.update(associationReqDTO);

        return updatedAssociation;
    }
}
