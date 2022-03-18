package com.petCommunity.PetCommunityBack.DomainModels;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean hasChip;
    private String race;
    private String size;
    private String age;
    private String specie;
    private Boolean vaccinated;
    private String description;

    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;



}
