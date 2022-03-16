package com.petCommunity.PetCommunityBack.DomainModels;


import lombok.*;
import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String password;
    private String logo;
    private String adress;
    private Integer capacity;

}
