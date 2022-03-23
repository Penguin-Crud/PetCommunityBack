package com.petCommunity.PetCommunityBack.DomainModels;


import lombok.*;
import javax.persistence.*;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean chip;
    private String race;
    private String size;
    private String age;
    private String specie;
    private Boolean vaccines;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
