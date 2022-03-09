package com.petCommunity.PetCommunityBack.DomainModels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Ubication;
    private Boolean hasChip;
    private String race;
    private String img;
    private String size;
    private String age;
    private String specie;
    private Boolean vaccinated;
    private String asociation;
    private String description;

}
